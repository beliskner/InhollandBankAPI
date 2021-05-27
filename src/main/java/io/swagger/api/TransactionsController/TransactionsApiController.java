package io.swagger.api.TransactionsController;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseTransaction;
import io.swagger.model.DTO.TransactionDTO.*;
import io.swagger.model.Body;
import io.swagger.model.Transaction;
import io.swagger.security.AuthCheck;
import io.swagger.service.Holders.HolderService;
import io.swagger.service.Transaction.TransactionService;
import io.swagger.service.accounts.AccountsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;
import org.threeten.bp.LocalDate;
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

    @Autowired
    private ModelMapper modelMapper;

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
                    ReturnBodyTransaction returnBodyTransaction = modelMapper.map(transaction, ReturnBodyTransaction.class);
                    return new ResponseEntity(returnBodyTransaction, HttpStatus.CREATED);
                } else {
                    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to create transaction with given request");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient permissions or account does not belong to you");
            }
        }
        return new ResponseEntity<ReturnBodyTransaction>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ReturnBodyDeposit> createTransactionForDeposit(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new deposit transaction", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyDeposit body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
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
                    return new ResponseEntity(modelMapper.map(transaction, ReturnBodyDeposit.class), HttpStatus.CREATED);
                } else {
                    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to create transaction with given request");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient permissions or account does not belong to you");
            }
        }
        return new ResponseEntity<ReturnBodyDeposit>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ReturnBodyWithdrawal> createTransactionForWithdrawal(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new withdrawal transaction", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyWithdrawal body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
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
                    return new ResponseEntity(modelMapper.map(transaction, ReturnBodyWithdrawal.class), HttpStatus.CREATED);
                } else {
                    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to create transaction with given request");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient permissions or account does not belong to you");
            }
        }
        return new ResponseEntity<ReturnBodyWithdrawal>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ArrayOfTransactions> getAllTransactions(@Parameter(in = ParameterIn.QUERY, description = "Gets all transactions involving given IBAN" ,schema=@Schema()) @Valid @RequestParam(value = "iban", required = false) String iban,@Parameter(in = ParameterIn.QUERY, description = "Filter transactions from a start date (if no end date is defined end date is datetime.now)" ,schema=@Schema()) @Valid @RequestParam(value = "startDate", required = false) LocalDate startDate,@Parameter(in = ParameterIn.QUERY, description = "Filter transactions until specified date (if no start date is defined, first transaction is start date)" ,schema=@Schema()) @Valid @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<Transaction> transactionsList = transactionService.getAllTransactions();
            if (!transactionsList.isEmpty()) {
                return new ResponseEntity(modelMapper.map(transactionsList, ArrayOfTransactions.class), HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No transactions found");
            }
        }
        return new ResponseEntity<ArrayOfTransactions>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<TanDTO> getTanByTransactionId(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets a transaction by ID. A transaction is a balance change between two accounts, one account that subtracts currency which is added to the opposing account. Each transaction is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            TanDTO tanDTO = transactionService.getTANByTransactionId(id);
            if (tanDTO != null) {
                return new ResponseEntity(modelMapper.map(tanDTO, TanDTO.class), HttpStatus.OK);
            }
        }

        return new ResponseEntity<TanDTO>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ReturnBodyTransaction> getTransactionById(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets a transaction by ID. A transaction is a balance change between two accounts, one account that subtracts currency which is added to the opposing account. Each transaction is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Optional<Transaction> transaction = transactionService.getTransactionById(id);
            if(transaction.isPresent()) {
                return new ResponseEntity(modelMapper.map(transaction, ReturnBodyTransaction.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<ReturnBodyTransaction>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<ReturnBodyTransaction>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<TanVerificationDTO> verifyTransactionByTan(@Min(1)@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id,@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new transaction", required=true, schema=@Schema()) @Valid @RequestBody TanDTO body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            TanVerificationDTO tanVerification = transactionService.verifyTransactionByTan(id, body.getTAN());
            if(tanVerification.getSuccess()) {
                return new ResponseEntity(modelMapper.map(tanVerification, TanVerificationDTO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity(modelMapper.map(tanVerification, TanVerificationDTO.class), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<TanVerificationDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
