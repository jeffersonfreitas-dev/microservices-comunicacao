package com.microservice.productapi.product.dto;

import com.microservice.productapi.category.dto.CategoryResponse;
import com.microservice.productapi.product.model.Product;
import com.microservice.productapi.supplier.dto.SupplierResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Integer id;
    private String name;
    private Integer quantity;
    private CategoryResponse categoryResponse;
    private SupplierResponse supplierResponse;

    public static ProductResponse of(Product entity){
        return new ProductResponse().builder()
                .id(entity.getId())
                .name(entity.getName())
                .quantity(entity.getQuantity())
                .categoryResponse(CategoryResponse.of(entity.getCategory()))
                .supplierResponse(SupplierResponse.of(entity.getSupplier()))
                .build();
    }
}
