package com.microservice.productapi.product.service;

import com.microservice.productapi.category.service.CategoryService;
import com.microservice.productapi.product.dto.ProductRequest;
import com.microservice.productapi.product.dto.ProductResponse;
import com.microservice.productapi.product.model.Product;
import com.microservice.productapi.product.repository.ProductRepository;
import com.microservice.productapi.supplier.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    public ProductResponse save (ProductRequest request){
        var product = new Product().builder()
                .name(request.getName())
                .quantity(request.getQuantity())
                .category(categoryService.findById(request.getCategoryId()))
                .supplier(supplierService.findById(request.getSupplierId()))
                .build();
        product = repository.save(product);
        return ProductResponse.of(product);
    }
}
