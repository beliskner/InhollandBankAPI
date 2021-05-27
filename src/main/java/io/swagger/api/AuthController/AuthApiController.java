package io.swagger.api.AuthController;

import io.swagger.annotations.Api;
import io.swagger.model.DTO.AuthDTO.TokenResponse;
import io.swagger.model.DTO.AuthDTO.Login;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.service.Holders.HolderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")
@RestController
@Api(tags = {"Auth"})
public class AuthApiController implements AuthApi {

    private static final Logger log = LoggerFactory.getLogger(AuthApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private HolderService holderService;

    @org.springframework.beans.factory.annotation.Autowired
    public AuthApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<TokenResponse> loginHolder(@Parameter(in = ParameterIn.DEFAULT, description = "Request body to login a holder", required=true, schema=@Schema()) @Valid @RequestBody Login body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            String token = holderService.login(body.getEmail(), body.getPassword());
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(token);
            return new ResponseEntity(tokenResponse, HttpStatus.CREATED);
        }
        return new ResponseEntity<TokenResponse>(HttpStatus.NOT_IMPLEMENTED);
    }
}
