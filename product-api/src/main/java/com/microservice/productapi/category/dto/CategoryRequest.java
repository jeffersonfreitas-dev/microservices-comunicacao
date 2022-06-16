package com.microservice.productapi.category.dto;

public class CategoryRequest {

    private String name;

    public void setName(String name) {
        if(name.isBlank()) throw new IllegalArgumentException("Nome é obrigatório");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
