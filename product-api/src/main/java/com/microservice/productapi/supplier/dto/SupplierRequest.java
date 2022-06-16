package com.microservice.productapi.supplier.dto;

public class SupplierRequest {

    private String name;

    public void setName(String name) {
        if(name.isBlank()) throw new IllegalArgumentException("Nome é obrigatório");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
