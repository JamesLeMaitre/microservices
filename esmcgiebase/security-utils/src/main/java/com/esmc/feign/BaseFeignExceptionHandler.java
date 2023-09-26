package com.esmc.feign;

import feign.FeignException;
import feign.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class BaseFeignExceptionHandler  extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignStatusException(FeignException ex, Response response) {
        String bodyOfResponse = ex.getLocalizedMessage();
        return new ResponseEntity<Object>(bodyOfResponse, HttpStatus.CONFLICT);
    }
}
