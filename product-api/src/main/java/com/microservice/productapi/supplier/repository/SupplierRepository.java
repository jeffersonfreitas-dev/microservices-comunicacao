package com.microservice.productapi.supplier.repository;

import com.microservice.productapi.category.model.Category;
import com.microservice.productapi.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
