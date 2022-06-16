package com.microservice.productapi.config.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionDetail {

    private int status;
    private String message;
    private Date timestamp;
}
