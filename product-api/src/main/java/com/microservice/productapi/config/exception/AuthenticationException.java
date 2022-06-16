package com.microservice.productapi.config.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String msg) {
        super(msg);
    }
}
