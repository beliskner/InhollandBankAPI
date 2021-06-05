package io.swagger.api.holderapi.IT.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class HolderSteps {

    private HttpHeaders headers = new HttpHeaders();
    private String baseUrl = "http://localhost:8080/api/holders/";
    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    private String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW5rQGluaG9sbGFuZC5ubCIsImF1dGgiOiJST0xFX0VNUExPWUVFIiwiaWF0IjoxNjIyODkzMjExLCJleHAiOjE2MjI4OTY4MTF9.cBPW4ufLYIFbfDGpBaiSK8vfhGEvsLq7Yri3CKbCC7k";

    @When("Create a holder")
    public void createAHolder() throws URISyntaxException {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(authToken);
        String body = "{\n" +
                "  \"dailyLimit\": 5000,\n" +
                "  \"email\": \"john@doe.com\",\n" +
                "  \"firstName\": \"John\",\n" +
                "  \"lastName\": \"Doe\",\n" +
                "  \"password\": \"p4ssw0rd\",\n" +
                "  \"role\": \"Customer\"\n" +
                "}";
        URI uri = new URI(baseUrl);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @Then("The request status code is {int}")
    public void theRequestStatusCodeIs(int expected) {
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(expected, response);
    }


    @When("Update a minBalance van account {string} en minbalance {int}")
    public void updateAMinBalanceVanAccountEnMinbalance(String arg0, int arg1) {
    }
}
