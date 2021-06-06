package io.swagger.api.HoldersController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.istack.Nullable;
import io.swagger.annotations.Api;
import io.swagger.model.Account;
import io.swagger.model.DTO.AccountDTO.ArrayOfAccounts;
import io.swagger.model.DTO.HolderDTO.ArrayOfHolders;
import io.swagger.model.DTO.HolderDTO.BodyDailyLimit;
import io.swagger.model.DTO.HolderDTO.BodyHolderStatus;
import io.swagger.model.DTO.HolderDTO.RequestBodyHolder;
import io.swagger.model.DTO.HolderDTO.RequestBodyUpdateHolder;
import io.swagger.model.DTO.HolderDTO.ReturnBodyHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Enums.IncludeFrozen;
import io.swagger.model.Enums.Role;
import io.swagger.model.Holder;
import io.swagger.repository.HolderRepository;
import io.swagger.security.AuthCheck;
import io.swagger.service.Holders.HolderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.modelmapper.ModelMapper;
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

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")
@RestController
@Api(tags = {"Holders"})
public class HoldersApiController implements HoldersApi {

    private static final Logger log = LoggerFactory.getLogger(HoldersApiController.class);

    private final ObjectMapper objectMapper;

    private ModelMapper mapper;

    private final HttpServletRequest request;

    @Autowired
    private AuthCheck authCheck;

    @Autowired
    private HolderService holderService;
    private HolderRepository holderRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public HoldersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ReturnBodyHolder> createHolder(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new holder", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyHolder body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            if(holderService.checkIfEmailIsNew(body.getEmail())) {
                // Email not found, create holder
                try {
                    Holder holder = holderService.add(body);
                    return new ResponseEntity(holder, HttpStatus.CREATED);
                } catch (Exception e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                // Email already in use, throw error
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email already in use");
            }
        }
        return new ResponseEntity<ReturnBodyHolder>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ArrayOfHolders> getAllHolders(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema(allowableValues={ "Customer", "Employee" }
)) @Valid @RequestParam(value = "role", required = false) String role,@Parameter(in = ParameterIn.QUERY, description = "Filter by first name" ,schema=@Schema()) @Valid @RequestParam(value = "firstName", required = false) String firstName,@Parameter(in = ParameterIn.QUERY, description = "Filter by last name" ,schema=@Schema()) @Valid @RequestParam(value = "lastName", required = false) String lastName,@Parameter(in = ParameterIn.QUERY, description = "Include frozen holders to the results of all holders or not" , schema=@Schema(allowableValues={ "No", "Yes" }
)) @Valid @RequestParam(value = "includeFrozen", required = false) String includeFrozen) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Holder> holders = holderService.getAllHolders(Role.fromValue(role), firstName, lastName, IncludeFrozen.fromValue(includeFrozen));
                String json = objectMapper.writeValueAsString(holders);
                return new ResponseEntity<ArrayOfHolders>(objectMapper.readValue(json, ArrayOfHolders.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ArrayOfHolders>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ArrayOfHolders>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ReturnBodyHolder> getHolderById(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Gets a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                Holder holder = holderService.getHolderById(id);
                if (authCheck.isHolderMakingRequestOrEmployee(authentication, holder)) {
                    if (holder != null) {
                        String json = objectMapper.writeValueAsString(holder);
                        return new ResponseEntity<ReturnBodyHolder>(objectMapper.readValue(json, ReturnBodyHolder.class), HttpStatus.OK);
                    } else {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Holder with id " + id + " not found");
                    }
                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyHolder>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ReturnBodyHolder>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ReturnBodyHolder> updateHolderById(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Updates a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
    )) @PathVariable("id") Integer id,@Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder", required=true, schema=@Schema()) @Valid @RequestBody RequestBodyUpdateHolder body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Holder holderExist = holderService.getHolderById(id);
                if (holderExist != null) {
                    Holder holder = holderService.updateHolderByHolderId(id, body);
                    return new ResponseEntity(holder, HttpStatus.OK);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Holder with id " + id + " not found");
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyHolder>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ReturnBodyHolder>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ReturnBodyHolder> deleteHolderById(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Deletes a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
    )) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Holder holderExist = holderService.getHolderById(id);
                if (holderExist != null) {
                    Holder holder = holderService.deleteHolderById(id);
                    return new ResponseEntity(holder, HttpStatus.OK);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Holder with id " + id + " not found");
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReturnBodyHolder>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ReturnBodyHolder>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ArrayOfAccounts> getAccountsByHolderId(@Min(1)@Parameter(in = ParameterIn.PATH, description = "A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
    )) @PathVariable("id") Integer id,@Parameter(in = ParameterIn.QUERY, description = "Include closed accounts to the results of all the holder's accounts or not" ,schema=@Schema(allowableValues={ "No", "Yes" }
    )) @Valid @RequestParam(value = "includeClosed", required = false) String includeClosed) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                Holder holder = holderService.getHolderById(id);
                if (authCheck.isHolderMakingRequestOrEmployee(authentication, holder)) {
                    if (holder != null) {
                        List<Account> accounts = holderService.getAccountsByHolderId(id, includeClosed);
                        return new ResponseEntity(accounts, HttpStatus.OK);
                    } else {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Holder with id " + id + " not found");
                    }
                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ArrayOfAccounts>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ArrayOfAccounts>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<BodyDailyLimit> updateDailyLimitByHolderId(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Updates a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id,@Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder", required=true, schema=@Schema()) @Valid @RequestBody BodyDailyLimit body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                Holder holder = holderService.getHolderById(id);
                if (authCheck.isHolderMakingRequestOrEmployee(authentication, holder)) {
                    if (holder != null) {
                        Holder updatedHolder = holderService.updateDailyLimitByHolderId(id, body.getDailyLimit());
                        String json = objectMapper.writeValueAsString(updatedHolder);
                        return new ResponseEntity<BodyDailyLimit>(objectMapper.readValue(json, BodyDailyLimit.class), HttpStatus.OK);
                    } else {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Holder with id " + id + " not found");
                    }
                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<BodyDailyLimit>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<BodyDailyLimit>(HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<BodyHolderStatus> updateHolderStatusByHolderId(@Min(1)@Parameter(in = ParameterIn.PATH, description = "Updates a holder by ID. A holder is a person/entity with a portfolio of accounts Each holder is identified by a numeric `id`. ", required=true, schema=@Schema(allowableValues={  }, minimum="1"
)) @PathVariable("id") Integer id,@Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder's status", required=true, schema=@Schema()) @Valid @RequestBody BodyHolderStatus body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Holder holder = holderService.getHolderById(id);
                if (holder != null) {
                    Holder.StatusEnum status = Holder.StatusEnum.fromValue(body.getStatus().toString());
                    Holder updatedHolder = holderService.updateHolderStatusByHolderId(id, status);
                    return new ResponseEntity(updatedHolder, HttpStatus.OK);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Holder with id " + id + " not found");
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<BodyHolderStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<BodyHolderStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
