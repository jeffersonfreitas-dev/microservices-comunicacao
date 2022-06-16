package com.microservice.productapi.supplier.controller;

import com.microservice.productapi.category.dto.CategoryRequest;
import com.microservice.productapi.category.dto.CategoryResponse;
import com.microservice.productapi.category.service.CategoryService;
import com.microservice.productapi.supplier.dto.SupplierRequest;
import com.microservice.productapi.supplier.dto.SupplierResponse;
import com.microservice.productapi.supplier.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supplier")
@AllArgsConstructor
public class SupplierController {

    private final SupplierService service;

    @PostMapping
    public SupplierResponse save (@RequestBody SupplierRequest request){
        return service.save(request);
    }
}
