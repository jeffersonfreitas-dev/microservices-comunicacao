package com.microservice.productapi.supplier.dto;

import com.microservice.productapi.supplier.model.Supplier;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class SupplierResponse {
    private Integer id;
    private String name;

    public static SupplierResponse of(Supplier entity) {
        var response = new SupplierResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
