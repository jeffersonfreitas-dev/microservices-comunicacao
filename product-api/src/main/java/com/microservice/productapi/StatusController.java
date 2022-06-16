package com.microservice.productapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("api/status")
public class StatusController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HashMap<String, Object>> status(){
        HashMap<String, Object> status = new HashMap<>();
        status.put("service", "Product-API");
        status.put("status", "up");
        status.put("httpStatus", HttpStatus.OK);
        return ResponseEntity.ok(status);
    }
}
