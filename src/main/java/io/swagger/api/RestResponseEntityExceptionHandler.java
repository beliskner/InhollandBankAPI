package io.swagger.api;

import io.swagger.model.ResponseCodes.ExceptionDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { ResponseStatusException.class })
    protected ResponseEntity<Object> handleConflict(ResponseStatusException ex, WebRequest request) {
        ExceptionDTO dto = new ExceptionDTO(ex.getReason());
        return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.valueOf(ex.getStatus().value()), request);
    }
}