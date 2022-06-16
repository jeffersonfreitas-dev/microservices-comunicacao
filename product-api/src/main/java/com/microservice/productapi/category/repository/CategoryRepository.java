package com.microservice.productapi.category.repository;

import com.microservice.productapi.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
