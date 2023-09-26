package com.esmc.gestionAgr.config;/*
package com.esmc.gestionAgr.config;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignStatusException(FeignException e, HttpServletResponse response) throws IOException {
        response.setStatus(e.status());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.status()));
    }

}
*/
