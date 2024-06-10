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

    private String name; // combine all the product with same name and only show the quantity
    private String description; // have
    private double price; // have

    @ElementCollection
    private Set<String> images; // have

    private String brand; // have

    @Enumerated(EnumType.STRING)
    public ProductSize size;
    public enum ProductSize {
        S, M, L
    }

    private String weight; // need to add to frontend
    private String dimension; // need to add to frontend
    private double discountedPercentage; // have

    private Date createdDate;
    private Date updatedDate;
    private boolean isDeleted;

    private boolean isDiscounted;
    private boolean isSoldOut;
}
