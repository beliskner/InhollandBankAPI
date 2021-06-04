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
    private String baseUrl = "http://localhost:8080/api/account/";
    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    private String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW5rQGluaG9sbGFuZC5ubCIsImF1dGgiOiJST0xFX0VNUExPWUVFIiwiaWF0IjoxNjIyNzU3OTMyLCJleHAiOjE2MjI3NjE1MzJ9.4rFRHJS3iYfHkObYpZZytQAqrjqy5n1ShGI9LwJzRHw";



    @When("Delete a acount")
    public void deleteAAcount() {
    }

    @When("Create a acount")
    public void createAAcount()throws URISyntaxException  {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        String body = "{\n" +
                "  \"accountType\": CURRENT,\n" +
                "  \"maxTransfer\": \"500\",\n" +
                "  \"minBalance\": \"-500\",\n" +
                "  \"status\": \"OPEN\",\n" +
                "  \"holderId\": \"1\",\n" +
                "  \"iban\": \"nl00INHL0000000001\"\n" +
                "}";
        URI uri = new URI(baseUrl);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        responseEntity = template.postForEntity(uri, entity, String.class);

    }

    @Then("The a new account will be created")
    public void theANewAccountWillBeCreated() {
    }

    @When("ik all accounts ophaal")
    public void ikAllAccountsOphaal() throws URISyntaxException {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        URI uri = new URI(baseUrl);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.getForEntity(uri, String.class);

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


    @Then("The requested status code is {int}")
    public void theRequestedStatusCodeIs(int arg0) {
        
    }

    @Then("The requested changed status code is {int}")
    public void theRequestedChangedStatusCodeIs(int expected) {
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(expected, response);
    }
}
