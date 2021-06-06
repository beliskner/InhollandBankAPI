package io.swagger.api.accountApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseAccount;
import io.swagger.model.Holder;
import io.swagger.repository.HolderRepository;
import io.swagger.service.accounts.AccountsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc


class accountApiTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private Account accountThatPassesValidation;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountsService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public void startTest() {
        System.out.println("Start testing HoldersApiController");
        accountThatPassesValidation = new Account();
        //accountThatPassesValidation.set
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Before()
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @WithMockUser(roles = "EMPLOYEE", username = "bank@inholland.nl", password = "p4ssw0rd" )
    @Test
    public void whenPostRequestToAccountAndAccountBodyIsInvalid_ThenGetStatus400() throws Exception{
        Account account = new Account();
        account.setBalance(new BigDecimal(300));
        String jsonstring = objectMapper.writeValueAsString(account);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        mvc.perform(MockMvcRequestBuilders.post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonstring))
                .andExpect(status().is(400));
    }

    @WithMockUser(roles = "EMPLOYEE", username = "bank@inholland.nl", password = "p4ssw0rd" )
    @Test
    public void whenPostRequestToAccountAndAccountBodyIsValid_ThenGetStatus201() throws Exception{
        Account account = new Account();
        account.setAccountType(BaseAccount.AccountTypeEnum.CURRENT);
        account.setMaxTransfer(new BigDecimal("5000.00"));
        account.setMinBalance(new BigDecimal("-500.00"));
        account.setStatus(BaseAccount.StatusEnum.OPEN);
        account.setBalance(new BigDecimal("1000000.25"));
        account.setHolderId(1);

        String jsonstring = objectMapper.writeValueAsString(account);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        HolderRepository holderRepository = Mockito.mock(HolderRepository.class);
        Holder holder = new Holder();
        holder.setId(1);

        Mockito.when(holderRepository.findById(1)).thenReturn(holder);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);


        mvc.perform(MockMvcRequestBuilders.post("/accounts")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(jsonstring))
                .andExpect(status().is(201));
    }










    @WithMockUser(roles = "EMPLOYEE", username = "bank@inholland.nl", password = "p4ssw0rd" )
    @Test
    public void getAllAccounts() throws Exception {
        List<Account> allAccounts = new ArrayList<Account>();

        Account account = new Account();
        account.setAccountType(BaseAccount.AccountTypeEnum.CURRENT);
        account.setMaxTransfer(new BigDecimal("5000.00"));
        account.setMinBalance(new BigDecimal("-5000.00"));
        account.setStatus(BaseAccount.StatusEnum.OPEN);
        account.setBalance(new BigDecimal("1000000.25"));
        account.setHolderId(1);
        allAccounts.add(account);

        Account account2 = new Account();
        account.setAccountType(BaseAccount.AccountTypeEnum.CURRENT);
        account.setMaxTransfer(new BigDecimal("5000.00"));
        account.setMinBalance(new BigDecimal("-5000.00"));
        account.setStatus(BaseAccount.StatusEnum.OPEN);
        account.setBalance(new BigDecimal("1000000.25"));
        account.setHolderId(1);
        allAccounts.add(account2);

        given(accountService.getAllAccounts(null )).willReturn(allAccounts);


        mvc.perform(MockMvcRequestBuilders.get ("/accounts")
                .accept("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(status().isOk());


    }
}
