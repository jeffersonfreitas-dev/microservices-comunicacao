package com.microservice.productapi.config.exception;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(String msg) {
        super(msg);
    }
}
