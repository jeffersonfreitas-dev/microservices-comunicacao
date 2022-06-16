package com.microservice.productapi.category.controller;

import com.microservice.productapi.category.dto.CategoryRequest;
import com.microservice.productapi.category.dto.CategoryResponse;
import com.microservice.productapi.category.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public CategoryResponse save (@RequestBody CategoryRequest request){
        return service.save(request);
    }
}
