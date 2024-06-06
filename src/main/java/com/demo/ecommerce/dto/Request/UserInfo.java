package com.demo.ecommerce.dto.Request;

import com.demo.ecommerce.entity.Country;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class UserInfo {
    private UUID id;
    private Country country;
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private Date dob;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String image;
}
