package io.swagger.api.accountApi.IT.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class AccountSteps {
    private HttpHeaders headers = new HttpHeaders();
    private String baseUrl = "http://localhost:8080/api/accounts/";
    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    private String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW5rQGluaG9sbGFuZC5ubCIsImF1dGgiOiJST0xFX0VNUExPWUVFIiwiaWF0IjoxNjIyODM2NDMwLCJleHAiOjE2MjI4NDAwMzB9.zuNQCL1dbBErvalOL1_I2NWUj7E44qDnsRO-Au3NSHo";




    @When("Create a acount")
    public void createAAcount()throws URISyntaxException  {
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




    @When("ik een account status veranderd naar {string}")
    public void ikEenAccountStatusVeranderdNaar(String given) throws URISyntaxException{
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        URI uri = new URI(baseUrl);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }





//for http responses
    @Then("is de status van de request {int}")
    public void isDeStatusVanDeRequest(int expected) {
            int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(expected, response);

    }

    @When("Update a minBalance")
    public void updateAMinBalance() {
    }

//    @Then("is de status van de request {int}")
//    public void isDeStatusVanDeRequest(int expected) {
//        int response = responseEntity.getStatusCodeValue();
//        Assert.assertEquals(expected, response);
//    }


//    @When("ik all accounts ophaal")
//    public void ikAllAccountsOphaal() throws URISyntaxException {
//        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        headers.setBearerAuth(authToken);
//        URI uri = new URI(baseUrl);
//        HttpEntity<String> entity = new HttpEntity<>(null, headers);
//        responseEntity = template.getForEntity(uri, String.class);
//
//    }
//
//    @When("ik een account status verander naar {string}")
//    public void ikEenAccountStatusVeranderNaar(String status) throws  JSONException{
//        String response = responseEntity.getBody();
//        JSONObject jsonObject = new JSONObject(response);
//        System.out.println(jsonObject.toString());
//        Assert.assertEquals(status, jsonObject.getString("status"));
//
//    }
//
//    @Then("is de status van de request {int}")
//    public void isDeStatusVanDeRequest(int status) throws  JSONException{
//
//    }


}
