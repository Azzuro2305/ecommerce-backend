package com.demo.ecommerce.service;


import com.demo.ecommerce.dto.Request.ResetPasswordRequest;
import com.demo.ecommerce.dto.Request.UserInfo;
import com.demo.ecommerce.entity.Users;

import java.util.UUID;

public interface UsersService {
    Users createUser(Users user);
    Users createAdmin(Users user);
    Users validateUser(String email, String password);
    void resetPassword(ResetPasswordRequest resetPasswordRequest);
    void deleteUser(UUID userId);
    UserInfo updateUserInfo(UserInfo userInfo);
}
