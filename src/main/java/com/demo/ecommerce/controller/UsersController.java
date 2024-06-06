package com.demo.ecommerce.controller;

import com.demo.ecommerce.dto.Request.ResetPasswordRequest;
import com.demo.ecommerce.dto.Request.UserInfo;
import com.demo.ecommerce.dto.utility.HttpResponse;
import com.demo.ecommerce.entity.Users;
import com.demo.ecommerce.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
//@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin
public class UsersController {
    @Autowired
    private final UsersService usersService;

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminAccess() {
        return "Admin content";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userAccess() {
        return "User content";
    }

    @PostMapping("/register-user")
    public ResponseEntity<HttpResponse<Users>> createUser(@RequestBody Users user) {
        Users newUser = usersService.createUser(user);
        HttpResponse<Users> response = new HttpResponse<>(newUser, "User created successfully", HttpStatus.CREATED);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register-admin")
    public ResponseEntity<HttpResponse<Users>> createAdmin(@RequestBody Users user) {
        Users newUser = usersService.createAdmin(user);
        HttpResponse<Users> response = new HttpResponse<>(newUser, "Admin created successfully", HttpStatus.CREATED);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate-user")
    public ResponseEntity<HttpResponse<Users>> validateUser(@RequestBody Users users) {
        Users user = usersService.validateUser(users.getEmail(), users.getPassword());
        if (user != null) {
            HttpResponse<Users> response = new HttpResponse<>(user, "User validated successfully", HttpStatus.OK);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<HttpResponse<Boolean>> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        usersService.resetPassword(resetPasswordRequest);
        HttpResponse<Boolean> response = new HttpResponse<>(true, "Password reset successfully", HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-user-info")
    public ResponseEntity<HttpResponse<UserInfo>> updateUserInfo(@RequestBody UserInfo userInfo) {
        UserInfo updatedUserInfo = usersService.updateUserInfo(userInfo);
        HttpResponse<UserInfo> response = new HttpResponse<>(updatedUserInfo, "User info updated successfully", HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<HttpResponse<String>> deleteUser(@PathVariable UUID userId) {
        usersService.deleteUser(userId);
        HttpResponse<String> response = new HttpResponse<>("User deleted successfully", HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
}
