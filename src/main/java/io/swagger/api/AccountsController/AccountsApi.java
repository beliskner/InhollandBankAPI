/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api.AccountsController;

import io.swagger.model.DTO.AccountDTO.ArrayOfAccounts;
import java.math.BigDecimal;
import io.swagger.model.DTO.AccountDTO.BodyAccountStatus;
import io.swagger.model.ResponseCodes.Error;
import io.swagger.model.DTO.AccountDTO.MaxTransfer;
import io.swagger.model.DTO.AccountDTO.MinBalance;
import io.swagger.model.DTO.AccountDTO.RequestBodyAccount;
import io.swagger.model.DTO.AccountDTO.RequestBodyUpdateAccount;
import io.swagger.model.DTO.AccountDTO.ReturnBodyAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")
@Validated
public interface AccountsApi {

    @Operation(summary = "Create an Account", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Accounts" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ReturnBodyAccount.class))),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/accounts",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<ReturnBodyAccount> createAccount(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new account", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyAccount body);


    @Operation(summary = "Set Account status to closed by IBAN", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Accounts" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ReturnBodyAccount.class))),
        
        @ApiResponse(responseCode = "400", description = "The specified account IBAN is invalid (incorrect syntax)."),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/accounts/{iban}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<ReturnBodyAccount> deleteAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Deletes an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required=true, schema=@Schema()) @PathVariable("iban") String iban);


    @Operation(summary = "Gets Account by IBAN", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Accounts" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ReturnBodyAccount.class))),
        
        @ApiResponse(responseCode = "400", description = "The specified account IBAN is invalid (incorrect syntax)."),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/accounts/{iban}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ReturnBodyAccount> getAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Gets an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required=true, schema=@Schema()) @PathVariable("iban") String iban);


    @Operation(summary = "Gets a list of all Accounts", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Accounts" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ArrayOfAccounts.class))),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/accounts",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ArrayOfAccounts> getAllAccounts(@Parameter(in = ParameterIn.QUERY, description = "Include closed accounts to the results of all accounts or not" ,schema=@Schema(allowableValues={ "No", "Yes" }
)) @Valid @RequestParam(value = "includeClosed", required = false) String includeClosed);


    @Operation(summary = "Gets balance by iban", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Accounts" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BigDecimal.class))) })
    @RequestMapping(value = "/accounts/{iban}/balance",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<BigDecimal> getBalanceByIban(@Parameter(in = ParameterIn.PATH, description = "Gets an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required=true, schema=@Schema()) @PathVariable("iban") String iban);


    @Operation(summary = "Updates an Account by IBAN", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Accounts" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Updated", content = @Content(schema = @Schema(implementation = ReturnBodyAccount.class))),
        
        @ApiResponse(responseCode = "400", description = "The specified account IBAN is invalid (incorrect syntax)."),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/accounts/{iban}",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<ReturnBodyAccount> updateAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Updates an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required=true, schema=@Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.DEFAULT, description = "Request body to update account", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyUpdateAccount body);


    @Operation(summary = "Change account's minimal balance according to IBAN and max transfer request body", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Accounts" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Updated", content = @Content(schema = @Schema(implementation = BodyAccountStatus.class))),
        
        @ApiResponse(responseCode = "400", description = "The specified IBAN is invalid (not a number)."),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/accounts/{iban}/status",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<BodyAccountStatus> updateAccountStatusByIban(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("iban") Integer iban, @Parameter(in = ParameterIn.DEFAULT, description = "Request body to update an account's status", required=true, schema=@Schema()) @Valid @RequestBody BodyAccountStatus body);


    @Operation(summary = "Change account's daily max transfer according to IBAN and max transfer request body", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Accounts" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Updated", content = @Content(schema = @Schema(implementation = MaxTransfer.class))),
        
        @ApiResponse(responseCode = "400", description = "The specified IBAN is invalid (not a number)."),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/accounts/{iban}/maxtransfer",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<MaxTransfer> updateMaxTransferByIban(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("iban") Integer iban, @Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder", required=true, schema=@Schema()) @Valid @RequestBody MaxTransfer body);


    @Operation(summary = "Change account's minimal balance according to IBAN and max transfer request body", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Accounts" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Updated", content = @Content(schema = @Schema(implementation = MinBalance.class))),
        
        @ApiResponse(responseCode = "400", description = "The specified IBAN is invalid (not a number)."),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/accounts/{iban}/minbalance",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<MinBalance> updateMinBalanceByIban(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("iban") Integer iban, @Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder", required=true, schema=@Schema()) @Valid @RequestBody MinBalance body);

}
