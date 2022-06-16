package com.microservice.productapi.supplier.service;

import com.microservice.productapi.category.dto.CategoryRequest;
import com.microservice.productapi.category.dto.CategoryResponse;
import com.microservice.productapi.category.model.Category;
import com.microservice.productapi.category.repository.CategoryRepository;
import com.microservice.productapi.config.exception.IdNotFoundException;
import com.microservice.productapi.supplier.dto.SupplierRequest;
import com.microservice.productapi.supplier.dto.SupplierResponse;
import com.microservice.productapi.supplier.model.Supplier;
import com.microservice.productapi.supplier.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SupplierService {

    private SupplierRepository repository;

    public SupplierResponse save(SupplierRequest request){
        var entity = repository.save(Supplier.of(request));
        return SupplierResponse.of(entity);
    }

    public Supplier findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Fornecedor não localizado com o Id: " + id));
    }

    public SupplierResponse findByIdResponse(Integer id) {
        Supplier entity = findById(id);
        return SupplierResponse.of(entity);
    }

}
