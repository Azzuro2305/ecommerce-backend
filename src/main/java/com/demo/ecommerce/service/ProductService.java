package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.Request.NewProductRequest;
import com.demo.ecommerce.entity.Product;

import java.util.UUID;

public interface ProductService {
    Product addProduct(NewProductRequest newProductRequest);
    Product updateProduct(NewProductRequest newProductRequest, UUID productId);
    void deleteProduct(UUID productId);
}
