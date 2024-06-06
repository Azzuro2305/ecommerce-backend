package com.demo.ecommerce.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users users;

    @ManyToMany(mappedBy = "products")
    private Set<Orders> orders;

    private String name;
    private String description;
    private double price;

    @ElementCollection
    private Set<String> images;

    private String brand;
    private String color;
    private String size;
    private String weight;
    private String dimension;

    private Date createdDate;
    private Date updatedDate;
    private boolean isDeleted;

    private boolean isFeatured;
    private boolean isPromoted;
    private boolean isDiscounted;
    private boolean isAvailable;
    private boolean isSoldOut;
    private boolean isOutOfStock;
}
