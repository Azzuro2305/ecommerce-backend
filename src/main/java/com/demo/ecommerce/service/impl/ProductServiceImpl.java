package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.Request.NewProductRequest;
import com.demo.ecommerce.entity.Category;
import com.demo.ecommerce.entity.Product;
import com.demo.ecommerce.entity.Users;
import com.demo.ecommerce.exception.ResourceNotFoundException;
import com.demo.ecommerce.repo.CategoryRepo;
import com.demo.ecommerce.repo.ProductRepo;
import com.demo.ecommerce.repo.UsersRepo;
import com.demo.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final UsersRepo userRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public Product addProduct(NewProductRequest newProductRequest) {
        Users admin = userRepo.findById(newProductRequest.getAdminId())
                .orElse(null);
//        Product newProduct = modelMapper.map(newProductRequest, Product.class);
        Product newProduct = new Product();
        Category category = categoryRepo.findById(newProductRequest.getCategoryId())
                .orElse(null);
        newProduct.setUsers(admin);
        newProduct.setCategory(category);
        newProduct.setName(newProductRequest.getName());
        newProduct.setDescription(newProductRequest.getDescription());
        newProduct.setPrice(newProductRequest.getPrice());
        newProduct.setBrand(newProductRequest.getBrand());
        newProduct.setColor(newProductRequest.getColor());
        newProduct.setSize(newProductRequest.getSize());
        newProduct.setWeight(newProductRequest.getWeight());
        newProduct.setDimension(newProductRequest.getDimension());
        newProduct.setImages(newProductRequest.getImages());
        newProduct.setCreatedDate(new Date());
        newProduct.setUpdatedDate(new Date());
        productRepo.save(newProduct);
        return newProduct;
    }

    @Override
    public Product updateProduct(NewProductRequest newProductRequest, UUID productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
        Users admin = userRepo.findById(newProductRequest.getAdminId())
                .orElse(null);
        Category category = categoryRepo.findById(newProductRequest.getCategoryId())
                .orElse(null);
        product.setUsers(admin);
        product.setCategory(newProductRequest.getCategoryId() != null ? category : product.getCategory());
        product.setName(newProductRequest.getName() != null ? newProductRequest.getName() : product.getName());
        product.setDescription(newProductRequest.getDescription() != null ? newProductRequest.getDescription() : product.getDescription());
        product.setPrice(newProductRequest.getPrice() != 0 ? newProductRequest.getPrice() : product.getPrice());
        product.setBrand(newProductRequest.getBrand() != null ? newProductRequest.getBrand() : product.getBrand());
        product.setColor(newProductRequest.getColor() != null ? newProductRequest.getColor() : product.getColor());
        product.setSize(newProductRequest.getSize() != null ? newProductRequest.getSize() : product.getSize());
        product.setWeight(newProductRequest.getWeight() != null ? newProductRequest.getWeight() : product.getWeight());
        product.setDimension(newProductRequest.getDimension() != null ? newProductRequest.getDimension() : product.getDimension());
        product.setImages(newProductRequest.getImages() != null ? newProductRequest.getImages() : product.getImages());
        product.setUpdatedDate(new Date());
        return productRepo.save(product);
    }

    @Override
    public void deleteProduct(UUID productId) {
        productRepo.deleteById(productId);
    }
}
