package com.demo.ecommerce.controller;


import com.demo.ecommerce.dto.Request.NewProductRequest;
import com.demo.ecommerce.dto.utility.HttpResponse;
import com.demo.ecommerce.entity.Product;
import com.demo.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
@CrossOrigin
public class ProductController {
    @Autowired
    private final ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<HttpResponse<Product>> addProduct(@RequestBody NewProductRequest newProductRequest) {
        Product newProduct = productService.addProduct(newProductRequest);
        HttpResponse<Product> response = new HttpResponse<>(newProduct, "Product added successfully", HttpStatus.CREATED);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-product/{productId}")
    public ResponseEntity<HttpResponse<Product>> updateProduct(@RequestBody NewProductRequest newProductRequest, @PathVariable UUID productId) {
        Product updatedProduct = productService.updateProduct(newProductRequest, productId);
        HttpResponse<Product> response = new HttpResponse<>(updatedProduct, "Product updated successfully", HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity<HttpResponse<String>> deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
        HttpResponse<String> response = new HttpResponse<>("Product deleted successfully", HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sizes")
    public Product.ProductSize[] getProductSizes() {
        return Product.ProductSize.values();
    }
}
