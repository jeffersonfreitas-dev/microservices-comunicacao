package com.microservice.productapi.category.service;

import com.microservice.productapi.category.dto.CategoryRequest;
import com.microservice.productapi.category.dto.CategoryResponse;
import com.microservice.productapi.category.model.Category;
import com.microservice.productapi.category.repository.CategoryRepository;
import com.microservice.productapi.config.exception.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository repository;

    public CategoryResponse save(CategoryRequest request){
        var category = repository.save(Category.of(request));
        return CategoryResponse.of(category);
    }

    public Category findById(Integer categoryId) {
        return repository.findById(categoryId)
                .orElseThrow(() -> new IdNotFoundException("Categoria não localizada com o Id: " + categoryId));
    }

    public CategoryResponse findByIdResponse(Integer categoryId) {
        Category entity = findById(categoryId);
        return CategoryResponse.of(entity);
    }
}
