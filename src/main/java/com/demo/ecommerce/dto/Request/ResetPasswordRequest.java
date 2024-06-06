package com.demo.ecommerce.dto.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
}
