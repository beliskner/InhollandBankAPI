package io.swagger.api;

import io.swagger.model.ArrayOfAccounts;
import java.math.BigDecimal;
import io.swagger.model.Error;
import io.swagger.model.MaxTransfer;
import io.swagger.model.MinBalance;
import io.swagger.model.RequestBodyAccount;
import io.swagger.model.RequestBodyUpdateAccount;
import io.swagger.model.ReturnBodyAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-11T10:26:20.927Z[GMT]")
@RestController
public class AccountsApiController implements AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ReturnBodyAccount> createAccount(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new account", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyAccount body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ReturnBodyAccount>(objectMapper.readValue("\"\"", ReturnBodyAccount.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ReturnBodyAccount>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ReturnBodyAccount> deleteAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Deletes an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required=true, schema=@Schema()) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ReturnBodyAccount>(objectMapper.readValue("\"\"", ReturnBodyAccount.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ReturnBodyAccount>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ReturnBodyAccount> getAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Gets an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required=true, schema=@Schema()) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ReturnBodyAccount>(objectMapper.readValue("\"\"", ReturnBodyAccount.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ReturnBodyAccount>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ArrayOfAccounts> getAllAccounts() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ArrayOfAccounts>(objectMapper.readValue("[ \"\", \"\" ]", ArrayOfAccounts.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ArrayOfAccounts>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ArrayOfAccounts>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<BigDecimal> getBalanceByIban(@Parameter(in = ParameterIn.PATH, description = "Gets an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required=true, schema=@Schema()) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<BigDecimal>(objectMapper.readValue("500.25", BigDecimal.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<BigDecimal>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<BigDecimal>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ReturnBodyAccount> updateAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Updates an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required=true, schema=@Schema()) @PathVariable("iban") String iban,@Parameter(in = ParameterIn.DEFAULT, description = "Request body to update account", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyUpdateAccount body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ReturnBodyAccount>(objectMapper.readValue("\"\"", ReturnBodyAccount.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ReturnBodyAccount>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<MaxTransfer> updateMaxTransferByIban(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("iban") Integer iban,@Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder", required=true, schema=@Schema()) @Valid @RequestBody MaxTransfer body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<MaxTransfer>(objectMapper.readValue("{\n  \"maxTransfer\" : 500\n}", MaxTransfer.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<MaxTransfer>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<MaxTransfer>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<MinBalance> updateMinBalanceByIban(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("iban") Integer iban,@Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder", required=true, schema=@Schema()) @Valid @RequestBody MinBalance body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<MinBalance>(objectMapper.readValue("{\n  \"minBalance\" : 500\n}", MinBalance.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<MinBalance>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<MinBalance>(HttpStatus.NOT_IMPLEMENTED);
    }

}
