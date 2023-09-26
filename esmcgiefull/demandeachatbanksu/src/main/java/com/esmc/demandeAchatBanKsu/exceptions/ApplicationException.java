package com.esmc.demandeAchatBanKsu.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> InvalideArgument(MethodArgumentNotValidException ex){
        Map<String, String> errotMap= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errotMap.put(error.getField(), error.getDefaultMessage());
        });
            return errotMap;
    }
}
