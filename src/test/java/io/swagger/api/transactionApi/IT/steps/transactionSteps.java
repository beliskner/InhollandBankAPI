package io.swagger.api.transactionApi.IT.steps;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class transactionSteps {
    private HttpHeaders headers = new HttpHeaders();
    private String baseUrl = "http://localhost:8080/api/transactions";
    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    private String authToken =  "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW5rQGluaG9sbGFuZC5ubCIsImF1dGgiOiJST0xFX0VNUExPWUVFIiwiaWF0IjoxNjIyOTE3ODgzLCJleHAiOjE2MjI5MjE0ODN9.ZF4_mhrevryZFyFnekd6QT1pfp9tV2zcdDdZgmvqUPw";

    @When("Create a transaction between accounts")
    public void createATransactionBetweenAccounts() throws URISyntaxException{
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        String body = "{\n" +
                "  \"Amount\": 5,\n" +
                "  \"TransactionType\": \"Transfer\",\n" +
                "  \"fromAccount\": \"NL00INHO0000000001\",\n" +
                "  \"toAccount\": \"NL00INHO0000000002\"\n" +
                "}";
        URI uri = new URI(baseUrl+"/transfer");
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("Create a transaction for deposit")
    public void createATransactionForDeposit() throws URISyntaxException{
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        String body = "{\n" +
                "  \"Amount\": 666,\n" +
                "  \"TransactionType\": \"Deposit\",\n" +
                "  \"toAccount\": \"NL00INHO0000000002\"\n" +
                "}";
        URI uri = new URI(baseUrl+"/deposit");
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("Create a transaction for widrawl")
    public void createATransactionForWidrawl() throws URISyntaxException{
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        String body = "{\n" +
                "  \"Amount\": 6,\n" +
                "  \"TransactionType\": \"withdrawal\",\n" +
                "  \"toAccount\": \"NL00INHO0000000001\"\n" +
                "}";
        URI uri = new URI(baseUrl+"/withdrawal");
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("i get all transactions")
    public void iGetAllTransactions() {
    }

    @When("i get transactions by id")
    public void iGetTransactionsById() {
    }

    @When("i get tan transactions by id")
    public void iGetTanTransactionsById() {
    }

    @When("i verify transaction by tan")
    public void iVerifyTransactionByTan() {
    }

    @When("Get all transactions")
    public void getAllTransactions() {
    }

    @Then("The response is an {string} object")
    public void theResponseIsAnObject(String arg0) {
    }
    //for http responses
    @Then("is the status of the request {int}")
    public void isTheStatusOfTheRequest(int expected) {
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(expected, response);
    }
}
