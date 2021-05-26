package io.swagger.api.TransactionsController;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseTransaction;
import io.swagger.model.DTO.TransactionDTO.ArrayOfTransactions;
import io.swagger.model.Body;
import io.swagger.model.DTO.TransactionDTO.TanDTO;
import io.swagger.model.ResponseCodes.InlineResponse2001;
import io.swagger.model.Transaction;
import io.swagger.security.AuthCheck;
import io.swagger.service.Holders.HolderService;
import io.swagger.service.Transaction.TransactionService;
import io.swagger.service.accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;
import org.threeten.bp.LocalDate;
import io.swagger.model.DTO.TransactionDTO.RequestBodyDeposit;
import io.swagger.model.DTO.TransactionDTO.RequestBodyTransaction;
import io.swagger.model.DTO.TransactionDTO.RequestBodyWithdrawal;
import io.swagger.model.DTO.TransactionDTO.ReturnBodyDeposit;
import io.swagger.model.DTO.TransactionDTO.ReturnBodyTransaction;
import io.swagger.model.DTO.TransactionDTO.ReturnBodyWithdrawal;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")
@RestController
@Api(tags = {"Transactions"})
public class TransactionsApiController implements TransactionsApi {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private HolderService holderService;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private AuthCheck authCheck;

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ReturnBodyTransaction> createTransactionBetweenAccounts(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new transfer transaction", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyTransaction body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<Account> fromAccount = accountsService.getAccountByIban(body.getFromAccount());
                if(body.getToAccount() != null && !accountsService.getAccountByIban(body.getToAccount()).isPresent()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No account with IBAN " + body.getToAccount() + " found.");
                } else if (body.getFromAccount() != null && !fromAccount.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No account with IBAN " + body.getFromAccount() + " found.");
                } else if(body.getTransactionType().toString() != BaseTransaction.TransactionTypeEnum.TRANSFER.toString() && body.getTransactionType().toString() != BaseTransaction.TransactionTypeEnum.REFUND.toString()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Specified transaction type: '"+ body.getTransactionType().toString() + "' incorrect, must be Transfer or Refund");
                }
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                int performedHolderId = holderService.getHolderByEmail(authentication.getName()).getId();
                if (authCheck.isOwnerOfAccountOrEmployee(SecurityContextHolder.getContext().getAuthentication(), fromAccount.get())) {
                    Transaction transaction = transactionService.createTransaction(body, performedHolderId, authCheck.hasRole("EMPLOYEE"));
                    if(transaction != null) {
                        String json = objectMapper.writeValueAsString(transaction);
                        return new ResponseEntity<ReturnBodyTransaction>(objectMapper.readValue(json, ReturnBodyTransaction.class), HttpStatus.CREATED);
                    } else {
                        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to create transaction with given request");
                    }
                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient permissions or account does not belong to you");
                }
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyTransaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ReturnBodyTransaction>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ReturnBodyDeposit> createTransactionForDeposit(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new deposit transaction", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyDeposit body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<Account> toAccount = accountsService.getAccountByIban(body.getToAccount());
                if(body.getToAccount() != null && !toAccount.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No account with IBAN " + body.getToAccount() + " found.");
                }
                if(body.getTransactionType().toString() != BaseTransaction.TransactionTypeEnum.DEPOSIT.toString()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Specified transaction type: '"+ body.getTransactionType().toString() + "' incorrect. must be Deposit");
                }
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                int performedHolderId = holderService.getHolderByEmail(authentication.getName()).getId();
                if (authCheck.isOwnerOfAccountOrEmployee(SecurityContextHolder.getContext().getAuthentication(), toAccount.get())) {
                    Transaction transaction = transactionService.createDepositTransaction(body, performedHolderId, authCheck.hasRole("EMPLOYEE"));
                    if (transaction != null) {
                        String json = objectMapper.writeValueAsString(transaction);
                        return new ResponseEntity<ReturnBodyDeposit>(objectMapper.readValue(json, ReturnBodyDeposit.class), HttpStatus.CREATED);
                    } else {
                        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to create transaction with given request");
                    }
                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient permissions or account does not belong to you");
                }
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyDeposit>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ReturnBodyDeposit>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ReturnBodyWithdrawal> createTransactionForWithdrawal(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new withdrawal transaction", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyWithdrawal body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<Account> fromAccount = accountsService.getAccountByIban(body.getFromAccount());
                if(body.getFromAccount() != null && !fromAccount.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No account with IBAN " + body.getFromAccount() + " found.");
                }
                if(body.getTransactionType().toString() != BaseTransaction.TransactionTypeEnum.WITHDRAWAL.toString()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Specified transaction type: '"+ body.getTransactionType().toString() + "' incorrect, must be Withdrawal");
                }
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                int performedHolderId = holderService.getHolderByEmail(authentication.getName()).getId();
                if (authCheck.isOwnerOfAccountOrEmployee(SecurityContextHolder.getContext().getAuthentication(), fromAccount.get())) {
                    Transaction transaction = transactionService.createWithdrawalTransaction(body, performedHolderId, authCheck.hasRole("EMPLOYEE"));
                    if(transaction != null) {
                        String json = objectMapper.writeValueAsString(transaction);
                        return new ResponseEntity<ReturnBodyWithdrawal>(objectMapper.readValue(json, ReturnBodyWithdrawal.class), HttpStatus.CREATED);
                    } else {
                        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to create transaction with given request");
                    }
                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient permissions or account does not belong to you");
                }
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyWithdrawal>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ReturnBodyWithdrawal>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ArrayOfTransactions> getAllTransactions(@Parameter(in = ParameterIn.QUERY, description = "Gets all transactions involving given IBAN" ,schema=@Schema()) @Valid @RequestParam(value = "iban", required = false) String iban,@Parameter(in = ParameterIn.QUERY, description = "Filter transactions from a start date (if no end date is defined end date is datetime.now)" ,schema=@Schema()) @Valid @RequestParam(value = "startDate", required = false) LocalDate startDate,@Parameter(in = ParameterIn.QUERY, description = "Filter transactions until specified date (if no start date is defined, first transaction is start date)" ,schema=@Schema()) @Valid @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Transaction> transactionsList = transactionService.getAllTransactions();
                if (!transactionsList.isEmpty()) {
                    String json = objectMapper.writeValueAsString(transactionsList);
                    return new ResponseEntity<ArrayOfTransactions>(objectMapper.readValue(json, ArrayOfTransactions.class), HttpStatus.OK);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No transactions found");
                }
            } catch (JsonProcessingException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ArrayOfTransactions>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ArrayOfTransactions>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ArrayOfTransactions>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<TanDTO> getTanByTransactionId(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets a transaction by ID. A transaction is a balance change between two accounts, one account that subtracts currency which is added to the opposing account. Each transaction is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                TanDTO tanDTO = transactionService.getTANByTransactionId(id);
                if (tanDTO != null) {
                    System.out.println(tanDTO);
                    String json = objectMapper.writeValueAsString(tanDTO);
                    System.out.println(json);
                    return new ResponseEntity<TanDTO>(objectMapper.readValue(json, TanDTO.class), HttpStatus.OK);
                }
            } catch (JsonProcessingException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TanDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TanDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TanDTO>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ReturnBodyTransaction> getTransactionById(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets a transaction by ID. A transaction is a balance change between two accounts, one account that subtracts currency which is added to the opposing account. Each transaction is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<Transaction> transaction = transactionService.getTransactionById(id);
                if(transaction.isPresent()) {
                    String json = objectMapper.writeValueAsString(transaction);
                    return new ResponseEntity<ReturnBodyTransaction>(objectMapper.readValue(json, ReturnBodyTransaction.class), HttpStatus.OK);
                } else {
                    return new ResponseEntity<ReturnBodyTransaction>(HttpStatus.NOT_FOUND);
                }
            } catch (JsonProcessingException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyTransaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyTransaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ReturnBodyTransaction>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<InlineResponse2001> verifyTransactionByTan(@Min(1)@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id,@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new transaction", required=true, schema=@Schema()) @Valid @RequestBody Body body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<InlineResponse2001>(objectMapper.readValue("\"\"", InlineResponse2001.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<InlineResponse2001>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<InlineResponse2001>(HttpStatus.NOT_IMPLEMENTED);
    }

}
