package com.esmc.demandeAchatBanKsu.exceptions;

public class ErrorExceptionHandle extends RuntimeException{
    public ErrorExceptionHandle(String message) {
        super(message);
    }

    public ErrorExceptionHandle(String message, Throwable cause) {
        super(message, cause);
    }

}
