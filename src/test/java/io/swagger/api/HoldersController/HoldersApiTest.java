package io.swagger.api.HoldersController;

import io.swagger.model.Holder;
import io.swagger.service.Holders.HolderService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class HoldersApiTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HolderService holderService;

    @BeforeAll
    public void startTest() {
        System.out.println("Start testing HoldersApiController");

        holderService.addInitialHolders();
    }

    @Before()
    public void setup()
    {
        //Init MockMvc Object and build
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void getAllHoldersTest() throws Exception {
        // TODO: MockMvc gebruikt niet de DB. Dus users gemaakt in de applicatie bestaan niet. er moet een mockUser gemaakt worden om auth te fixen
        // TODO: ik weet ook niet hoe token dan meegegeven wordt met mockUser/mockAuth
        String token = "placeholder";
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/holders")
                .accept("application/json")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void createHolderShouldNotBeNull() {
        Holder holder = new Holder();
        assertNotNull(holder);
    }

    @Test
    public void getAllHoldersShouldReturnJsonArray() throws Exception {
        this.mvc.perform(get("/api/holders")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

}
