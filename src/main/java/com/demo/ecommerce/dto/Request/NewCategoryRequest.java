package com.demo.ecommerce.dto.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class NewCategoryRequest {
    private UUID adminId;
    private String name;
    private String description;
}
