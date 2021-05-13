/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.ArrayOfAccounts;
import io.swagger.model.ArrayOfHolders;
import io.swagger.model.BodyDailyLimit;
import io.swagger.model.BodyHolderStatus;
import io.swagger.model.Error;
import io.swagger.model.RequestBodyHolder;
import io.swagger.model.RequestBodyUpdateHolder;
import io.swagger.model.ReturnBodyHolder;
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
public interface HoldersApi {

    @Operation(summary = "Create a Holder", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Holders" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ReturnBodyHolder.class))),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/holders",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<ReturnBodyHolder> createHolder(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new holder", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyHolder body);


    @Operation(summary = "Set Holder status to frozen by id", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Holders" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ReturnBodyHolder.class))),
        
        @ApiResponse(responseCode = "400", description = "The specified holder ID is invalid (not a number)."),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/holders/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<ReturnBodyHolder> deleteHolderById(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Deletes a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id);


    @Operation(summary = "Get accounts according to Holder ID", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Holders" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ArrayOfAccounts.class))),
        
        @ApiResponse(responseCode = "400", description = "The specified holder ID is invalid (not a number)."),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/holders/{id}/accounts",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ArrayOfAccounts> getAccountsByHolderId(@Min(1)@Parameter(in = ParameterIn.PATH, description = "A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.QUERY, description = "Include closed accounts to the results of all the holder's accounts or not" ,schema=@Schema(allowableValues={ "No", "Yes" }
)) @Valid @RequestParam(value = "includeClosed", required = false) String includeClosed);


    @Operation(summary = "Gets a list of all Holders (optional per role, per first name or per last name)", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Holders" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ArrayOfHolders.class))),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/holders",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ArrayOfHolders> getAllHolders(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema(allowableValues={ "Customer", "Employee" }
)) @Valid @RequestParam(value = "role", required = false) String role, @Parameter(in = ParameterIn.QUERY, description = "Filter by first name" ,schema=@Schema()) @Valid @RequestParam(value = "firstName", required = false) String firstName, @Parameter(in = ParameterIn.QUERY, description = "Filter by last name" ,schema=@Schema()) @Valid @RequestParam(value = "lastName", required = false) String lastName, @Parameter(in = ParameterIn.QUERY, description = "Include frozen holders to the results of all holders or not" ,schema=@Schema(allowableValues={ "No", "Yes" }
)) @Valid @RequestParam(value = "includeFrozen", required = false) String includeFrozen);


    @Operation(summary = "Gets Holder by ID", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Holders" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ReturnBodyHolder.class))),
        
        @ApiResponse(responseCode = "400", description = "The specified holder ID is invalid (not a number)."),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/holders/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ReturnBodyHolder> getHolderById(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id);


    @Operation(summary = "Change holder's daily limit according to holder ID and daily limit request body", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Holders" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Updated", content = @Content(schema = @Schema(implementation = BodyDailyLimit.class))),
        
        @ApiResponse(responseCode = "400", description = "The specified holder ID is invalid (not a number)."),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/holders/{id}/dailylimit",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<BodyDailyLimit> updateDailyLimitByHolderId(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Updates a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder", required=true, schema=@Schema()) @Valid @RequestBody BodyDailyLimit body);


    @Operation(summary = "Updates a Holder by ID", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Holders" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Updated", content = @Content(schema = @Schema(implementation = ReturnBodyHolder.class))),
        
        @ApiResponse(responseCode = "400", description = "The specified holder ID is invalid (not a number)."),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/holders/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<ReturnBodyHolder> updateHolderById(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Updates a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyUpdateHolder body);


    @Operation(summary = "Change holder's status to frozen/unfrozen with status body", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Holders" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Updated", content = @Content(schema = @Schema(implementation = BodyHolderStatus.class))),
        
        @ApiResponse(responseCode = "400", description = "The specified holder ID is invalid (not a number)."),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "403", description = "Unauthorized request.", content = @Content(schema = @Schema(implementation = Error.class))),
        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/holders/{id}/status",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<BodyHolderStatus> updateHolderStatusByHolderId(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Updates a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder's status", required=true, schema=@Schema()) @Valid @RequestBody BodyHolderStatus body);

}

