/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.ArrayOfTransactions;
import io.swagger.model.Body;
import io.swagger.model.Error;
import io.swagger.model.InlineResponse200;
import io.swagger.model.InlineResponse2001;
import org.threeten.bp.LocalDate;
import io.swagger.model.RequestBodyDeposit;
import io.swagger.model.RequestBodyTransaction;
import io.swagger.model.RequestBodyWithdrawal;
import io.swagger.model.ReturnBodyDeposit;
import io.swagger.model.ReturnBodyTransaction;
import io.swagger.model.ReturnBodyWithdrawal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")
@Validated
public interface TransactionsApi {

    @Operation(summary = "Create a transfer Transaction between two accounts", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Transactions" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ReturnBodyTransaction.class))),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/transactions/transfer",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<ReturnBodyTransaction> createTransactionBetweenAccounts(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new transfer transaction", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyTransaction body);


    @Operation(summary = "Create a deposit Transaction", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Transactions" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ReturnBodyDeposit.class))),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/transactions/deposit",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<ReturnBodyDeposit> createTransactionForDeposit(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new deposit transaction", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyDeposit body);


    @Operation(summary = "Create a withdrawal Transaction", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Transactions" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ReturnBodyWithdrawal.class))),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/transactions/withdrawal",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<ReturnBodyWithdrawal> createTransactionForWithdrawal(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new withdrawal transaction", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyWithdrawal body);


    @Operation(summary = "Gets a list of all Transactions", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Transactions" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ArrayOfTransactions.class))),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/transactions",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ArrayOfTransactions> getAllTransactions(@Parameter(in = ParameterIn.QUERY, description = "Gets all transactions involving given IBAN" ,schema=@Schema()) @Valid @RequestParam(value = "iban", required = false) String iban, @Parameter(in = ParameterIn.QUERY, description = "Filter transactions from a start date (if no end date is defined end date is datetime.now)" ,schema=@Schema()) @Valid @RequestParam(value = "startDate", required = false) LocalDate startDate, @Parameter(in = ParameterIn.QUERY, description = "Filter transactions until specified date (if no start date is defined, first transaction is start date)" ,schema=@Schema()) @Valid @RequestParam(value = "endDate", required = false) LocalDate endDate);


    @Operation(summary = "Gets TAN by transaction ID", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Transactions" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = InlineResponse200.class))),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/transactions/{id}/tan",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<InlineResponse200> getTanByTransactionId(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets a transaction by ID. A transaction is a balance change between two accounts, one account that subtracts currency which is added to the opposing account. Each transaction is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id);


    @Operation(summary = "Gets Transaction by ID", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Transactions" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ReturnBodyTransaction.class))),
        
        @ApiResponse(responseCode = "400", description = "The specified transaction ID is invalid (not a number)."),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/transactions/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ReturnBodyTransaction> getTransactionById(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets a transaction by ID. A transaction is a balance change between two accounts, one account that subtracts currency which is added to the opposing account. Each transaction is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id);


    @Operation(summary = "Verify TAN to Approve transaction", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Transactions" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "TAN correct", content = @Content(schema = @Schema(implementation = InlineResponse2001.class))),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/transactions/{id}/tan",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<InlineResponse2001> verifyTransactionByTan(@Min(1)@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new transaction", required=true, schema=@Schema()) @Valid @RequestBody Body body);

}

