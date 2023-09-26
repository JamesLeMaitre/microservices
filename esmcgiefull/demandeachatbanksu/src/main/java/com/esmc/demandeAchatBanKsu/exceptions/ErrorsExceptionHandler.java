package com.esmc.demandeAchatBanKsu.exceptions;

import com.esmc.demandeAchatBanKsu.entities.Errors;
import com.esmc.demandeAchatBanKsu.repositories.ErrorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ErrorsExceptionHandler {
    private final ErrorRepository errorRepository;

    public ErrorsExceptionHandler(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    @ExceptionHandler(value = {ErrorExceptionHandle.class})
    public ResponseEntity<Object> handleException(ErrorExceptionHandle handle){
        HttpStatus request = HttpStatus.BAD_REQUEST;
        ErrorException errorException = new ErrorException(handle.getMessage(),handle,
                request, ZonedDateTime.now(ZoneId.of("Z")));
        Errors er = new Errors();
        er.setMessage(handle.getMessage());
        er.setHttpStatus(request);
        er.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Z")));
        er.setThrowable(handle.toString());
        errorRepository.save(er);
        return new ResponseEntity<>(errorException,request);
    }
}
