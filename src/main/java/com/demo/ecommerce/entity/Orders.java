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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;

    private int quantity;
    private double total;
    private String status;
    private String payment_status;

    private String shipping_address;
    private String shipping_city;
    private String shipping_state;
    private String shipping_country;
    private int shipping_zip;

    private String shipping_phone;
    private String shipping_email;

    private String shipping_status;
    private String shipping_date;
    private String tracking_number;

    private boolean isDeleted;
    private Date createdDate;
    private Date updatedDate;

    private boolean isCancelled;
    private boolean isReturned;
    private boolean isRefunded;
    private boolean isShipped;
}
