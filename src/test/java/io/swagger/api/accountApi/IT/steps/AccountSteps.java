package io.swagger.api.accountApi.IT.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class AccountSteps {
    private HttpHeaders headers = new HttpHeaders();
    private String baseUrl = "http://localhost:8080/api/holders/";
    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    private String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW5rQGluaG9sbGFuZC5ubCIsImF1dGgiOiJST0xFX0VNUExPWUVFIiwiaWF0IjoxNjIyNzU3OTMyLCJleHAiOjE2MjI3NjE1MzJ9.4rFRHJS3iYfHkObYpZZytQAqrjqy5n1ShGI9LwJzRHw";


    @When("Delete a acount")
    public void deleteAAcount() {
    }

    @When("Create a acount")
    public void createAAcount() {

    }

    @Then("The a new account will be created")
    public void theANewAccountWillBeCreated() {
    }

    @When("ik all accounts ophaal")
    public void ikAllAccountsOphaal() {
    }

    @Then("krijg ik een lijst van accounts")
    public void krijgIkEenLijstVanAccounts() {
    }

    @When("ik een acount delete")
    public void ikEenAcountDelete() {
    }

    @Then("word de status closed")
    public void wordDeStatusClosed() {
    }

    @When("Update a acount status")
    public void updateAAcountStatus() {
    }

    @When("Update a minBalance")
    public void updateAMinBalance() {
    }

    @When("Update a maxTransfer")
    public void updateAMaxTransfer() {
    }
}
