package io.swagger.api;

import io.swagger.model.ArrayOfAccounts;
import io.swagger.model.ArrayOfHolders;
import io.swagger.model.BodyDailyLimit;
import io.swagger.model.Error;
import io.swagger.model.RequestBodyHolder;
import io.swagger.model.RequestBodyUpdateHolder;
import io.swagger.model.ReturnBodyHolder;
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
public class HoldersApiController implements HoldersApi {

    private static final Logger log = LoggerFactory.getLogger(HoldersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public HoldersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ReturnBodyHolder> createHolder(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new holder", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyHolder body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ReturnBodyHolder>(objectMapper.readValue("\"\"", ReturnBodyHolder.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyHolder>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ReturnBodyHolder>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ReturnBodyHolder> deleteHolderById(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Deletes a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ReturnBodyHolder>(objectMapper.readValue("\"\"", ReturnBodyHolder.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyHolder>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ReturnBodyHolder>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ArrayOfAccounts> getAccountsByHolderId(@Min(1)@Parameter(in = ParameterIn.PATH, description = "A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id) {
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

    public ResponseEntity<ArrayOfHolders> getAllHolders(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema(allowableValues={ "Customer", "Employee" }
)) @Valid @RequestParam(value = "role", required = false) String role,@Parameter(in = ParameterIn.QUERY, description = "Filter by first name" ,schema=@Schema()) @Valid @RequestParam(value = "firstName", required = false) String firstName,@Parameter(in = ParameterIn.QUERY, description = "Filter by last name" ,schema=@Schema()) @Valid @RequestParam(value = "lastName", required = false) String lastName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ArrayOfHolders>(objectMapper.readValue("[ \"\", \"\" ]", ArrayOfHolders.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ArrayOfHolders>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ArrayOfHolders>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ReturnBodyHolder> getHolderById(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ReturnBodyHolder>(objectMapper.readValue("\"\"", ReturnBodyHolder.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyHolder>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ReturnBodyHolder>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<BodyDailyLimit> updateDailyLimitByHolderId(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Updates a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id,@Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder", required=true, schema=@Schema()) @Valid @RequestBody BodyDailyLimit body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<BodyDailyLimit>(objectMapper.readValue("{\n  \"dailyLimit\" : 500\n}", BodyDailyLimit.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<BodyDailyLimit>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<BodyDailyLimit>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ReturnBodyHolder> updateHolderById(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Updates a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id,@Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyUpdateHolder body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ReturnBodyHolder>(objectMapper.readValue("\"\"", ReturnBodyHolder.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyHolder>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ReturnBodyHolder>(HttpStatus.NOT_IMPLEMENTED);
    }

}
