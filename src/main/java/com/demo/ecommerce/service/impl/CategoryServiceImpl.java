package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.Request.NewCategoryRequest;
import com.demo.ecommerce.entity.Category;
import com.demo.ecommerce.entity.Users;
import com.demo.ecommerce.repo.CategoryRepo;
import com.demo.ecommerce.repo.UsersRepo;
import com.demo.ecommerce.service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final UsersRepo userRepo;
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public Category addCategory(NewCategoryRequest newCategoryRequest) {
        Users admin = userRepo.findById(newCategoryRequest.getAdminId())
                .orElse(null);
        Category newCategory = modelMapper.map(newCategoryRequest, Category.class);
        newCategory.setCreatedDate(new Date());
        newCategory.setUpdatedDate(new Date());
        newCategory.setUsers(admin);
        return categoryRepo.save(newCategory);
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        categoryRepo.deleteById(categoryId);
    }
}
