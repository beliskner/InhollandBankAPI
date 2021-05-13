/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.InlineResponse201;
import io.swagger.model.Login;
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
public interface AuthApi {

    @Operation(summary = "Login a Holder", description = "", tags={ "Auth" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Logged in", content = @Content(schema = @Schema(implementation = InlineResponse201.class))),
        
        @ApiResponse(responseCode = "401", description = "Authorization information is missing or invalid.", content = @Content(schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/auth/login",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<InlineResponse201> loginHolder(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to login a holder", required=true, schema=@Schema()) @Valid @RequestBody Login body);


    @Operation(summary = "Logs the user out by invalidating their token", description = "", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Auth" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Logged out") })
    @RequestMapping(value = "/auth/logout",
        method = RequestMethod.POST)
    ResponseEntity<Void> logoutHolder();

}

