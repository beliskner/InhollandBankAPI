package io.swagger.api.HoldersController;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.api.NotFoundException;
import io.swagger.helpers.MapListsHelper;
import io.swagger.model.Account;
import io.swagger.model.DTO.AccountDTO.ArrayOfAccounts;
import io.swagger.model.DTO.HolderDTO.ArrayOfHolders;
import io.swagger.model.DTO.HolderDTO.BodyDailyLimit;
import io.swagger.model.DTO.HolderDTO.BodyHolderStatus;
import io.swagger.model.DTO.HolderDTO.RequestBodyHolder;
import io.swagger.model.DTO.HolderDTO.RequestBodyUpdateHolder;
import io.swagger.model.DTO.HolderDTO.ReturnBodyHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Holder;
import io.swagger.security.AuthCheck;
import io.swagger.service.Holders.HolderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.AccountException;
import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.lang.reflect.Array;
import java.util.List;

import static io.swagger.helpers.MapListsHelper.modelMapper;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")
@RestController
@Api(tags = {"Holders"})
public class HoldersApiController implements HoldersApi {

    private static final Logger log = LoggerFactory.getLogger(HoldersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private HolderService holderService;

    @Autowired
    public AuthCheck authCheck;

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
)) @PathVariable("id") Integer id,@Parameter(in = ParameterIn.QUERY, description = "Include closed accounts to the results of all the holder's accounts or not" ,schema=@Schema(allowableValues={ "No", "Yes" }
)) @Valid @RequestParam(value = "includeClosed", required = false) String includeClosed) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Account> accounts = holderService.getAccountsByHolderId(id);
                if (!accounts.isEmpty()) {
                    String json = objectMapper.writeValueAsString(accounts);
                    return new ResponseEntity<ArrayOfAccounts>(objectMapper.readValue(json, ArrayOfAccounts.class), HttpStatus.OK);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No holders are found");
                }
            } catch (JsonProcessingException e) {
                log.error("Couldn't process Json", e);
                return new ResponseEntity<ArrayOfAccounts>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ArrayOfAccounts>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ArrayOfAccounts>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ArrayOfHolders> getAllHolders(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema(allowableValues={ "Customer", "Employee" }
)) @Valid @RequestParam(value = "role", required = false) String role,@Parameter(in = ParameterIn.QUERY, description = "Filter by first name" ,schema=@Schema()) @Valid @RequestParam(value = "firstName", required = false) String firstName,@Parameter(in = ParameterIn.QUERY, description = "Filter by last name" ,schema=@Schema()) @Valid @RequestParam(value = "lastName", required = false) String lastName,@Parameter(in = ParameterIn.QUERY, description = "Include frozen holders to the results of all holders or not" ,schema=@Schema(allowableValues={ "No", "Yes" }
)) @Valid @RequestParam(value = "includeFrozen", required = false) String includeFrozen) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Holder> holders = holderService.getAllHolders();
                if (!holders.isEmpty()) {
                    String json = objectMapper.writeValueAsString(holders);
                    return new ResponseEntity<ArrayOfHolders>(objectMapper.readValue(json, ArrayOfHolders.class), HttpStatus.OK);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No holders are found");
                }
            } catch(JsonProcessingException e) {
                log.error("Couldn't process Json", e);
                return new ResponseEntity<ArrayOfHolders>(HttpStatus.INTERNAL_SERVER_ERROR);
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
                Holder holder = holderService.getHolderById(id);
                if (holder != null) {
                    String json = objectMapper.writeValueAsString(holder);
                    return new ResponseEntity<ReturnBodyHolder>(objectMapper.readValue(json, ReturnBodyHolder.class), HttpStatus.OK);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Holder with id " + id + " not found");
                }
            } catch(JsonProcessingException e) {
                log.error("Couldn't process Json", e);
                return new ResponseEntity<ReturnBodyHolder>(HttpStatus.INTERNAL_SERVER_ERROR);
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
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                boolean isHolderMakingRequestOrEmployee = authCheck.isHolderMakingRequestOrEmployee(authentication, id);
                if (isHolderMakingRequestOrEmployee) {
                    Holder holder = holderService.updateDailyLimitByHolderId(id, body.getDailyLimit());
                    String json = objectMapper.writeValueAsString(holder);
                    return new ResponseEntity<BodyDailyLimit>(objectMapper.readValue(json, BodyDailyLimit.class), HttpStatus.OK);
                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
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

    public ResponseEntity<BodyHolderStatus> updateHolderStatusByHolderId(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Updates a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id,@Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder's status", required=true, schema=@Schema()) @Valid @RequestBody BodyHolderStatus body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<BodyHolderStatus>(objectMapper.readValue("{\n  \"status\" : \"Active\"\n}", BodyHolderStatus.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<BodyHolderStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<BodyHolderStatus>(HttpStatus.NOT_IMPLEMENTED);
    }

}
