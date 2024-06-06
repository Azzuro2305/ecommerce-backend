package com.demo.ecommerce.controller;

import com.demo.ecommerce.dto.Request.NewCategoryRequest;
import com.demo.ecommerce.dto.utility.HttpResponse;
import com.demo.ecommerce.entity.Category;
import com.demo.ecommerce.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
@CrossOrigin
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @PostMapping("/add-category")
    public ResponseEntity<HttpResponse<Category>> addCategory(@RequestBody NewCategoryRequest newCategoryRequest) {
        Category newCategory = categoryService.addCategory(newCategoryRequest);
        HttpResponse<Category> response = new HttpResponse<>(newCategory, "Category created successfully", HttpStatus.CREATED);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-category/{categoryId}")
    public ResponseEntity<HttpResponse<String>> deleteCategory(@PathVariable UUID categoryId) {
        categoryService.deleteCategory(categoryId);
        HttpResponse<String> response = new HttpResponse<>("Category deleted successfully", HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
}
