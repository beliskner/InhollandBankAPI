package io.swagger.api.TransactionsController;

import io.swagger.annotations.Api;
import io.swagger.model.DTO.TransactionDTO.ArrayOfTransactions;
import io.swagger.model.Body;
import io.swagger.model.ResponseCodes.InlineResponse200;
import io.swagger.model.ResponseCodes.InlineResponse2001;
import org.springframework.security.access.prepost.PreAuthorize;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")
@RestController
@Api(tags = {"Transactions"})
public class TransactionsApiController implements TransactionsApi {

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
                return new ResponseEntity<ReturnBodyTransaction>(objectMapper.readValue("\"\"", ReturnBodyTransaction.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyTransaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ReturnBodyTransaction>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ReturnBodyDeposit> createTransactionForDeposit(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new deposit transaction", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyDeposit body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ReturnBodyDeposit>(objectMapper.readValue("\"\"", ReturnBodyDeposit.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyDeposit>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ReturnBodyDeposit>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ReturnBodyWithdrawal> createTransactionForWithdrawal(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new withdrawal transaction", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyWithdrawal body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ReturnBodyWithdrawal>(objectMapper.readValue("\"\"", ReturnBodyWithdrawal.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyWithdrawal>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ReturnBodyWithdrawal>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ArrayOfTransactions> getAllTransactions(@Parameter(in = ParameterIn.QUERY, description = "Gets all transactions involving given IBAN" ,schema=@Schema()) @Valid @RequestParam(value = "iban", required = false) String iban,@Parameter(in = ParameterIn.QUERY, description = "Filter transactions from a start date (if no end date is defined end date is datetime.now)" ,schema=@Schema()) @Valid @RequestParam(value = "startDate", required = false) LocalDate startDate,@Parameter(in = ParameterIn.QUERY, description = "Filter transactions until specified date (if no start date is defined, first transaction is start date)" ,schema=@Schema()) @Valid @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ArrayOfTransactions>(objectMapper.readValue("[ \"\", \"\" ]", ArrayOfTransactions.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ArrayOfTransactions>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ArrayOfTransactions>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<InlineResponse200> getTanByTransactionId(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets a transaction by ID. A transaction is a balance change between two accounts, one account that subtracts currency which is added to the opposing account. Each transaction is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<InlineResponse200>(objectMapper.readValue("{\n  \"TAN\" : 1234,\n  \"id\" : 1\n}", InlineResponse200.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<InlineResponse200>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<InlineResponse200>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ReturnBodyTransaction> getTransactionById(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets a transaction by ID. A transaction is a balance change between two accounts, one account that subtracts currency which is added to the opposing account. Each transaction is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ReturnBodyTransaction>(objectMapper.readValue("\"\"", ReturnBodyTransaction.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyTransaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ReturnBodyTransaction>(HttpStatus.NOT_IMPLEMENTED);
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
