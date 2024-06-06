package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.Request.NewCategoryRequest;
import com.demo.ecommerce.entity.Category;

import java.util.UUID;

public interface CategoryService {
    Category addCategory(NewCategoryRequest newCategoryRequest);
    void deleteCategory(UUID categoryId);
}
