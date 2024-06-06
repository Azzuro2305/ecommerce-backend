package com.demo.ecommerce.dto.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class NewProductRequest {
    private UUID adminId;
    private UUID categoryId;
    private String name;
    private String description;
    private double price;
    private Set<String> images;
    private String brand;
    private String color;
    private String size;
    private String weight;
    private String dimension;
}
