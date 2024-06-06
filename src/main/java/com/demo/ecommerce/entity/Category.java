package com.demo.ecommerce.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users users;

//    @OneToMany(mappedBy = "category")  // check this again
//    private Set<Product> products;

    private String name;
    private String description;

    private Date createdDate;
    private Date updatedDate;
    private boolean isDeleted;
}
