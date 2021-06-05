package io.swagger.api.HoldersController.IT.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.Gson;
import io.swagger.configuration.ObjectMapper;
import io.swagger.model.DTO.HolderDTO.ArrayOfHolders;
import io.swagger.model.DTO.HolderDTO.BodyDailyLimit;
import io.swagger.model.Enums.Role;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class HolderSteps {

    // "email": "bank@inholland.nl",
    // "email": "peter@appel.com",

    private String authTokenEmployee = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW5rQGluaG9sbGFuZC5ubCIsImF1dGgiOiJST0xFX0VNUExPWUVFIiwiaWF0IjoxNjIyOTI3MDE0LCJleHAiOjE2MjI5MzA2MTR9.mNvUyOUkNuvUztOGf9pc8s8O4H2_YXMllvXEGK2zGlY";
    private String authTokenCustomer = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwZXRlckBhcHBlbC5jb20iLCJhdXRoIjoiUk9MRV9DVVNUT01FUiIsImlhdCI6MTYyMjkxMDkwMSwiZXhwIjoxNjIyOTE0NTAxfQ.LzZLRcVxbZI4zC5W-rTUOACWrsZNOasPySzFU-kb-dA";
    private HttpHeaders emptyHeaders = new HttpHeaders();
    private String baseUrl = "http://localhost:8080/api/holders/";
    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;

    public HttpHeaders setHeaders(HttpHeaders headers, Boolean isEmployee) {
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        if(isEmployee) {
            headers.setBearerAuth(authTokenEmployee);
        } else {
            headers.setBearerAuth(authTokenCustomer);
        }

        return headers;
    }

    // ---------- Tests for get all holders ----------
    @When("Get all holders")
    public void getAllHolders() throws URISyntaxException {
        HttpHeaders headers = setHeaders(emptyHeaders, true);
        URI uri = new URI(baseUrl);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
    }

    @Then("The response is an {string} object")
    public void theResponseIsAnObject(String objectName) {
        ArrayOfHolders arrayOfHolders = new Gson().fromJson(responseEntity.getBody(), ArrayOfHolders.class);
        Assert.assertFalse(arrayOfHolders.isEmpty());
        Assert.assertEquals(objectName, arrayOfHolders.getClass().getSimpleName());
    }

    @When("Get all holders by role {string}")
    public void getAllHoldersByRole(String role) throws URISyntaxException {
        HttpHeaders headers = setHeaders(emptyHeaders, true);
        URI uri = new URI(baseUrl + "?role=" + role);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
    }

    @Then("The holders have role {string}")
    public void theHoldersHaveRole(String role) throws JSONException {
        String response = responseEntity.getBody();
        JSONArray jsonArray = new JSONArray(response);
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject holder = jsonArray.getJSONObject(i);
            Object actualRole = holder.get("role");
            Assert.assertEquals(role, actualRole);
        }
    }

    @And("The array holds {int} holders")
    public void theArrayHoldsHolders(int count) throws JSONException {
        String response = responseEntity.getBody();
        JSONArray jsonArray = new JSONArray(response);
        Assert.assertEquals(count, jsonArray.length());
    }

    @When("Get all holders by first name {string}")
    public void getAllHoldersByFirstName(String nameSearch) throws URISyntaxException {
        HttpHeaders headers = setHeaders(emptyHeaders, true);
        URI uri = new URI(baseUrl + "?firstName=" + nameSearch);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
    }

    @Then("The holders have {string} in their first name")
    public void theHoldersHaveInTheirFirstName(String nameSearch) throws JSONException {
        String response = responseEntity.getBody();
        JSONArray jsonArray = new JSONArray(response);
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject holder = jsonArray.getJSONObject(i);
            String actualName = holder.get("firstName").toString().toLowerCase();
            Assert.assertTrue(actualName.contains(nameSearch.toLowerCase()));
        }
    }

    @When("Getting all holders as customer")
    public void gettingAllHoldersAsCustomer() throws URISyntaxException, JSONException {
        HttpHeaders headers = setHeaders(emptyHeaders, false);
        URI uri = new URI(baseUrl);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        try {
            responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
        } catch(HttpStatusCodeException e) {
            // Error 403 expected, set field for the then test
            errorCode = e.getStatusCode().value();
        }

    }

    // ---------- Tests for create holder ----------
    @When("Create a holder")
    public void createAHolder() throws URISyntaxException {
        HttpHeaders headers = setHeaders(emptyHeaders, true);
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

    @When("Failing to create a holder with email {string}")
    public void failingToCreateAHolderWithEmail(String email) throws URISyntaxException {
        HttpHeaders headers = setHeaders(emptyHeaders, true);
        String body = "{\n" +
                "  \"dailyLimit\": 5000,\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"firstName\": \"John\",\n" +
                "  \"lastName\": \"Doe\",\n" +
                "  \"password\": \"p4ssw0rd\",\n" +
                "  \"role\": \"Customer\"\n" +
                "}";
        URI uri = new URI(baseUrl);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        try {
            responseEntity = template.postForEntity(uri, entity, String.class);
        } catch(HttpStatusCodeException e) {
            // Error 422 expected, set field for the then test
            errorCode = e.getStatusCode().value();
            System.out.println(e.getStatusCode().value());
        }

    }

    // ---------- Tests for get holder by id ----------
    @When("Getting a holder with id {int}")
    public void gettingAHolderWithId(int id) throws URISyntaxException {
        HttpHeaders headers = setHeaders(emptyHeaders, true);
        URI uri = new URI(baseUrl + id);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
    }

    @And("The holder email is {string}")
    public void theHolderEmailIs(String email) throws JSONException {
        String response = responseEntity.getBody();
        JSONObject holder = new JSONObject(response);
        Assert.assertEquals(email, holder.get("email"));
    }

    // ---------- Tests for update holder by id ----------
    @When("Update holder with id {int} to first name {string}")
    public void updateHolderWithIdToFirstName(int id, String firstName) throws URISyntaxException {
        HttpHeaders headers = setHeaders(emptyHeaders, true);
        URI uri = new URI(baseUrl + id);
        String body = "{\n" +
                "  \"dailyLimit\": 250,\n" +
                "  \"email\": \"bank@inholland.nl\",\n" +
                "  \"firstName\": \"" + firstName + "\",\n" +
                "  \"lastName\": \"Bank\",\n" +
                "  \"role\": \"Employee\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        responseEntity = template.exchange(uri, HttpMethod.PUT, entity, String.class);
    }

    @And("The holder first name is {string}")
    public void theHolderFirstNameIs(String firstName) throws JSONException {
        String response = responseEntity.getBody();
        JSONObject holder = new JSONObject(response);
        Assert.assertEquals(firstName, holder.get("firstName"));
    }

    // ---------- Tests for soft deleting a holder by id ----------
    @When("Delete holder with id {int}")
    public void deleteHolderWithId(int id) throws URISyntaxException {
        HttpHeaders headers = setHeaders(emptyHeaders, true);
        URI uri = new URI(baseUrl + id);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.exchange(uri, HttpMethod.DELETE, entity, String.class);
    }

    @And("The holder status is {string}")
    public void theHolderStatusIs(String status) throws JSONException {
        String response = responseEntity.getBody();
        JSONObject holder = new JSONObject(response);
        Assert.assertEquals(status, holder.get("status"));
    }

    // ---------- Tests for expected status response ----------
    @Then("The request status code is {int}")
    public void theRequestStatusCodeIs(int expected) {
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(expected, response);
    }

    private int errorCode = 0;

    @Then("The bad request status code is {int}")
    public void theBadRequestStatusCodeIs(int expected) {
        System.out.println("badreq status code is");
        System.out.println(expected);
        System.out.println(errorCode);
        Assert.assertEquals(expected, errorCode);
        // reset error code for other bad request tests
        errorCode = 0;
    }



}
