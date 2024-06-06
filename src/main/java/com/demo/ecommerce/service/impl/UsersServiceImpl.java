package com.demo.ecommerce.service.impl;


import com.demo.ecommerce.dto.Request.ResetPasswordRequest;
import com.demo.ecommerce.dto.Request.UserInfo;
import com.demo.ecommerce.entity.Users;
import com.demo.ecommerce.repo.UsersRepo;
import com.demo.ecommerce.service.UsersService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepo usersRepo;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public Users createUser(Users user) {
        Users newuser = new Users();
        newuser.setFirst_name(user.getFirst_name());
        newuser.setLast_name(user.getLast_name());
        newuser.setUsername(user.getFirst_name() + " " + user.getLast_name());
        newuser.setCreatedDate(new Date());
        newuser.setUpdatedDate(new Date());
        newuser.setRoles(Set.of("USER"));
        newuser.setEmail(user.getEmail());
        newuser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return usersRepo.save(newuser);
    }

    @Override
    public Users createAdmin(Users user) {
        Users newuser = new Users();
        newuser.setFirst_name(user.getFirst_name());
        newuser.setLast_name(user.getLast_name());
        newuser.setUsername(user.getFirst_name() + " " + user.getLast_name());
        newuser.setCreatedDate(new Date());
        newuser.setUpdatedDate(new Date());
        newuser.setRoles(Set.of("ADMIN"));
        newuser.setEmail(user.getEmail());
        newuser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return usersRepo.save(newuser);
    }

    @Override
    public Users validateUser(String email, String password) {
        Users user = usersRepo.findByEmail(email);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Users user = usersRepo.findByEmail(resetPasswordRequest.getEmail());
        if (user != null) {
            if (bCryptPasswordEncoder.matches(resetPasswordRequest.getOldPassword(), user.getPassword())) {
                user.setPassword(bCryptPasswordEncoder.encode(resetPasswordRequest.getNewPassword()));
                usersRepo.save(user);
            }
        }
    }

    @Override
    public void deleteUser(UUID userId) {
        usersRepo.deleteById(userId);
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        Users user = usersRepo.findById(userInfo.getId())
                .orElse(null);
        if (user != null) {
            user.setCountry(userInfo.getCountry() != null ? userInfo.getCountry() : user.getCountry());
            user.setFirst_name(userInfo.getFirst_name() != null ? userInfo.getFirst_name() : user.getFirst_name());
            user.setLast_name(userInfo.getLast_name() != null ? userInfo.getLast_name() : user.getLast_name());
            user.setUsername(userInfo.getUsername() != null ? userInfo.getUsername() : user.getUsername());
            user.setEmail(userInfo.getEmail() != null ? userInfo.getEmail() : user.getEmail());
            user.setDob(userInfo.getDob() != null ? userInfo.getDob() : user.getDob());
            user.setPhone(userInfo.getPhone() != null ? userInfo.getPhone() : user.getPhone());
            user.setAddress(userInfo.getAddress() != null ? userInfo.getAddress() : user.getAddress());
            user.setCity(userInfo.getCity() != null ? userInfo.getCity() : user.getCity());
            user.setState(userInfo.getState() != null ? userInfo.getState() : user.getState());
            user.setZip(userInfo.getZip() != null ? userInfo.getZip() : user.getZip());
            user.setImage(userInfo.getImage() != null ? userInfo.getImage() : user.getImage());
            user.setUpdatedDate(new Date());
            usersRepo.save(user);
        }
        return modelMapper.map(user, UserInfo.class);
    }
}
