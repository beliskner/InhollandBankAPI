package io.swagger.api.transactionApi.IT.steps;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.h2.util.json.JSONArray;
import org.h2.util.json.JSONObject;
import org.json.JSONException;
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
    private String authToken =  "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW5rQGluaG9sbGFuZC5ubCIsImF1dGgiOiJST0xFX0VNUExPWUVFIiwiaWF0IjoxNjIyOTIwNjU5LCJleHAiOjE2MjI5MjQyNTl9.fhAR7PcMvEFNB3F32n9hS7UGrQh-AZdvYmyEi2J_I_U";

    @When("Create a transaction between accounts")
    public void createATransactionBetweenAccounts() throws URISyntaxException{
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        String body = "{\n" +
                "  \"Amount\": 51,\n" +
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
                "  \"Amount\": 6,\n" +
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
                "  \"Amount\": 5.00,\n" +
                "  \"TransactionType\": \"Withdrawal\",\n" +
                "  \"fromAccount\": \"NL00INHO0000000002\"\n" +
                "}";
        URI uri = new URI(baseUrl+"/withdrawal");
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("i get all transactions")
    public void iGetAllTransactions() throws URISyntaxException {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        URI uri = new URI(baseUrl + "?iban=NL00INHO0000000002");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
    }

    @When("i get transactions by id is {int}")
    public void iGetTransactionsByIdIs(int id) throws URISyntaxException {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        URI uri = new URI(baseUrl + "?/"+id);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
}

    @When("i get tan transactions by id is {int}")
    public void iGetTanTransactionsByIdIs(int id)throws URISyntaxException {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        URI uri = new URI(baseUrl + "?/"+id+"/tan");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
    }

    @When("i verify transaction by tan")
    public void iVerifyTransactionByTan() throws URISyntaxException {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        String body = "{\n" +
                "  \"TAN\": 0\n" +
                "}}";
        URI uri = new URI(baseUrl+"/5/tan");
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("Get all transactions")
    public void getAllTransactions() throws URISyntaxException {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
            URI uri = new URI(baseUrl + "?iban=NL00INHO0000000002");
            HttpEntity<String> entity = new HttpEntity<>(null, headers);
            responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
    }

    @Then("The response is an {string} object")
    public void theResponseIsAnObject(String transfers)  throws JSONException {
        String response = responseEntity.getBody();
        JSONArray jsonArray = new JSONArray(response);
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject holder = jsonArray.getJSONObject(i);
            String actualName = holder.get("firstName").toString().toLowerCase();
            Assert.assertTrue(actualName.contains(transfers.toLowerCase()));
        }
    }
    //for http responses
    @Then("is the status of the request {int}")
    public void isTheStatusOfTheRequest(int expected) {
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(expected, response);
    }

}
