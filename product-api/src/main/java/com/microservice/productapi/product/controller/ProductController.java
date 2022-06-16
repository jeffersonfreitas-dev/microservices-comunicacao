package com.microservice.productapi.product.controller;

import com.microservice.productapi.category.dto.CategoryRequest;
import com.microservice.productapi.category.dto.CategoryResponse;
import com.microservice.productapi.category.service.CategoryService;
import com.microservice.productapi.product.dto.ProductRequest;
import com.microservice.productapi.product.dto.ProductResponse;
import com.microservice.productapi.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ProductResponse save (@RequestBody ProductRequest request){
        return service.save(request);
    }
}
