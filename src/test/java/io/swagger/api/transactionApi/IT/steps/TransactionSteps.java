package io.swagger.api.transactionApi.IT.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class TransactionSteps {

    private HttpHeaders headers = new HttpHeaders();
    private String baseUrl = "http://localhost:8080/api/holders/";
    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    private String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW5rQGluaG9sbGFuZC5ubCIsImF1dGgiOiJST0xFX0VNUExPWUVFIiwiaWF0IjoxNjIyNzU3OTMyLCJleHAiOjE2MjI3NjE1MzJ9.4rFRHJS3iYfHkObYpZZytQAqrjqy5n1ShGI9LwJzRHw";




}
