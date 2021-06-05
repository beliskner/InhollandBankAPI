package io.swagger.api.accountApi.IT.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.Account;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;


public class AccountSteps {
    private HttpHeaders headers = new HttpHeaders();
    private String baseUrl = "http://localhost:8080/api/accounts";
    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    private String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW5rQGluaG9sbGFuZC5ubCIsImF1dGgiOiJST0xFX0VNUExPWUVFIiwiaWF0IjoxNjIyODg0NTUwLCJleHAiOjE2MjI4ODgxNTB9.Oek2QvwbTazNslslBNyGlSqckv4lYk-IKc6JEWk2wZg";


    @When("Create a acount")
    public void createAAcount() throws URISyntaxException {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        String body = "{\n" +
                "  \"accountType\": \"Current\",\n" +
                "  \"holderId\": 1,\n" +
                "  \"iban\": \"string\",\n" +
                "  \"maxTransfer\": 500,\n" +
                "  \"minBalance\": -500.25,\n" +
                "  \"status\": \"Open\"\n" +
                "}";
        URI uri = new URI(baseUrl);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        responseEntity = template.postForEntity(uri, entity, String.class);

    }

    @When("i get all accounts")
    public void iGetAllAccounts() throws URISyntaxException {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        URI uri = new URI(baseUrl + "?includeClosed=No");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
    }


    @When("i do a status change to closed on account with iban {string}")
    public void iDoAStatusChangeToClosedOnAccountWithIban(String iban) throws URISyntaxException {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        URI uri = new URI(baseUrl + "/" + iban);

        responseEntity = template.exchange(uri, HttpMethod.DELETE, entity, String.class);
    }


    @When("Update a maxTransfer of account with iban {string}")
    public void updateAMaxTransferOfAccountWithIban(String iban) throws URISyntaxException {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        String body = "{\n" +
                "  \"maxTransfer\": 500\n" +
                "}";
        URI uri = new URI(baseUrl + "/" + iban + "/maxtransfer");
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        responseEntity = template.exchange(uri, HttpMethod.PUT, entity, String.class);
    }


    @When("Update a minBalance van accounts {string}")
    public void updateAMinBalanceVanAccounts(String iban) throws URISyntaxException {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        String body = "{\n" +
                "  \"minBalance\": 500\n" +
                "}";
        URI uri = new URI(baseUrl + "/" + iban + "/minbalance");
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        responseEntity = template.exchange(uri, HttpMethod.PUT, entity, String.class);
    }

    //for http responses
    @Then("is the status of the request {int}")
    public void isTheStatusOfTheRequest(int expected) {
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(expected, response);
    }



}


