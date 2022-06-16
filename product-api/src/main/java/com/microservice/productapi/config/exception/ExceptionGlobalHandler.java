package com.microservice.productapi.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handlerIllegalArgumentException(IllegalArgumentException ex){
        var exceptionDetail = new ExceptionDetail();
        exceptionDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionDetail.setMessage(ex.getMessage());
        exceptionDetail.setTimestamp(new Date());
        return ResponseEntity.badRequest().body(exceptionDetail);
    }

}
