package com.demo.ecommerce.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String first_name;
    private String last_name;
    private String username;
    private Date dob;
    private String email;
    private String password;
//    private String role;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    public Collection<GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String image;

    private Date createdDate;
    private Date updatedDate;
    private boolean isDeleted;

    private int loginAttempt;
    private boolean isLocked;
    private boolean isVerified;
    private boolean isBlocked;
    private boolean isSuspended;
    private boolean isBanned;
}
