package io.swagger.api.AccountsController;

import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import io.swagger.helpers.MapListsHelper;
import io.swagger.model.Account;
import io.swagger.model.DTO.AccountDTO.ArrayOfAccounts;

import java.math.BigDecimal;

import io.swagger.model.DTO.AccountDTO.BodyAccountStatus;
import io.swagger.model.DTO.AccountDTO.MaxTransfer;
import io.swagger.model.DTO.AccountDTO.MinBalance;
import io.swagger.model.DTO.AccountDTO.RequestBodyAccount;
import io.swagger.model.DTO.AccountDTO.RequestBodyUpdateAccount;
import io.swagger.model.DTO.AccountDTO.ReturnBodyAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.security.AuthCheck;
import io.swagger.service.accounts.AccountsService;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static io.swagger.helpers.MapListsHelper.modelMapper;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")
@RestController
@Api(tags = {"Accounts"})
public class AccountsApiController implements AccountsApi {

    @Autowired
    private AuthCheck authCheck;

    @Autowired
    AccountsService accountsService;
    @Autowired
    private ModelMapper mapper;

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ReturnBodyAccount> createAccount(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to create a new account", required = true, schema = @Schema()) @Valid @RequestBody RequestBodyAccount body) {
        String accept = request.getHeader("Accept");
        if (accept == null || !accept.contains("application/json"))
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            ReturnBodyAccount returnBodyAccount = mapper.map(accountsService.addAccount(body), ReturnBodyAccount.class);
            return new ResponseEntity<ReturnBodyAccount>(returnBodyAccount, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ReturnBodyAccount> deleteAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Deletes an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required = true, schema = @Schema()) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        if (accept == null || !accept.contains("application/json"))
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            Account account = accountsService.deleteAccount(iban);
            return new ResponseEntity<ReturnBodyAccount>(mapper.map(account, ReturnBodyAccount.class), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ReturnBodyAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('EMPLOYEE')or hasRole('CUSTOMER')")
    public ResponseEntity<ReturnBodyAccount> getAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Gets an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required = true, schema = @Schema()) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        if (accept == null || !accept.contains("application/json"))
            return new ResponseEntity<ReturnBodyAccount>(HttpStatus.NOT_IMPLEMENTED);
        try {
            Optional<Account> optionalAccount = accountsService.getAccountByIban(iban);
            if (!optionalAccount.isPresent()) return new ResponseEntity(HttpStatus.NOT_FOUND);

            if (authCheck.isOwnerOfAccountOrEmployee(SecurityContextHolder.getContext().getAuthentication(), optionalAccount.get())) {
                return new ResponseEntity<ReturnBodyAccount>(mapper.map(optionalAccount.get(), ReturnBodyAccount.class), HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Permission insufficient or specified account does not belong to you");
            }
        } catch (Exception e) {
            return new ResponseEntity<ReturnBodyAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('EMPLOYEE')or hasRole('CUSTOMER')")
    public ResponseEntity<ArrayOfAccounts> getAllAccounts(@Parameter(in = ParameterIn.QUERY, description = "Include closed accounts to the results of all accounts or not", schema = @Schema(allowableValues = {"No", "Yes"}
    )) @Valid @RequestParam(value = "includeClosed", required = false) String includeClosed) {
        String accept = request.getHeader("Accept");
        if (accept == null || !accept.contains("application/json"))
            return new ResponseEntity<ArrayOfAccounts>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(modelMapper.map(accountsService.getAllAccounts(includeClosed), ArrayOfAccounts.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE')or hasRole('CUSTOMER')")
    public ResponseEntity<BigDecimal> getBalanceByIban(@Parameter(in = ParameterIn.PATH, description = "Gets an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required = true, schema = @Schema()) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        if (accept == null || !accept.contains("application/json")) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            return new ResponseEntity<BigDecimal>(objectMapper.readValue("500.25", BigDecimal.class), HttpStatus.NOT_IMPLEMENTED);
        } catch (IOException e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<BigDecimal>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ReturnBodyAccount> updateAccountByIban(@Parameter(in = ParameterIn.PATH, description = "Updates an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required = true, schema = @Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.DEFAULT, description = "Request body to update account", required = true, schema = @Schema()) @Valid @RequestBody RequestBodyUpdateAccount body) {
        String accept = request.getHeader("Accept");
        if (accept == null || !accept.contains("application/json"))
            return new ResponseEntity<ReturnBodyAccount>(HttpStatus.BAD_REQUEST);
        try {
            Account account = accountsService.updateStatusAccount(iban, body);
            ReturnBodyAccount returnBodyAccount = mapper.map(account, ReturnBodyAccount.class);

            return new ResponseEntity<ReturnBodyAccount>(returnBodyAccount, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<ReturnBodyAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PreAuthorize("hasRole('EMPLOYEE')or hasRole('CUSTOMER')")
    public ResponseEntity<BodyAccountStatus> updateAccountStatusByIban(String iban, @Valid BodyAccountStatus body) {
        String accept = request.getHeader("Accept");

        if (accept == null || !accept.contains("application/json"))
            return new ResponseEntity<BodyAccountStatus>(HttpStatus.NOT_IMPLEMENTED);
        try {
            Account account = accountsService.deleteAccount(iban);
            return new ResponseEntity<BodyAccountStatus>(mapper.map(account, BodyAccountStatus.class), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<BodyAccountStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<MaxTransfer> updateMaxTransferByIban(@Parameter(in = ParameterIn.PATH, description = "Gets an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required = true, schema = @Schema(allowableValues = {}
    )) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder", required = true, schema = @Schema()) @Valid @RequestBody MaxTransfer body) {
        String accept = request.getHeader("Accept");
        if (accept == null || !accept.contains("application/json"))
            return new ResponseEntity<MaxTransfer>(HttpStatus.BAD_REQUEST);
        try {
            return new ResponseEntity<MaxTransfer>(accountsService.updateMaxTransferByIban(iban, body), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<MaxTransfer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<MinBalance> updateMinBalanceByIban(@Parameter(in = ParameterIn.PATH, description = "Gets an account by IBAN. An account is a balance of currency owned by a holder. Each account is identified by a string identifier `iban`. ", required = true, schema = @Schema(allowableValues = {}
    )) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.DEFAULT, description = "Request body to update a holder", required = true, schema = @Schema()) @Valid @RequestBody MinBalance body) {
        String accept = request.getHeader("Accept");
        if (accept == null || !accept.contains("application/json"))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            MinBalance minBalance = accountsService.updateMinAccount(iban, body);
            return new ResponseEntity<MinBalance>(minBalance, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<MinBalance>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}