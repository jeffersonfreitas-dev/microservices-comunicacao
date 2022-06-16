package com.microservice.productapi.product.dto;

public class ProductRequest {

    private String name;
    private Integer quantity;
    private Integer categoryId;
    private Integer supplierId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.isBlank()) throw new IllegalArgumentException("Nome é obrigatório");
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if(quantity == null) throw new IllegalArgumentException("Quantidade é obrigatório");
        if(quantity < 0) throw new IllegalArgumentException("Quantidade não pode ser negativo");
        this.quantity = quantity;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        if(categoryId == null) throw new IllegalArgumentException("Categoria é obrigatória");
        this.categoryId = categoryId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        if(supplierId == null) throw new IllegalArgumentException("Fornecedor é obrigatório");
        this.supplierId = supplierId;
    }
}
