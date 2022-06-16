package com.microservice.productapi.product.repository;

import com.microservice.productapi.category.model.Category;
import com.microservice.productapi.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
