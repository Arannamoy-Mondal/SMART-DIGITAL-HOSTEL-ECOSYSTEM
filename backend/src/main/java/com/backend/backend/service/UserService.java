package com.backend.backend.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.backend.dto.UserRequest;
import com.backend.backend.model.Role;
import com.backend.backend.model.User;
import com.backend.backend.repo.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private RoleService roleService;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    public User saveUser(UserRequest userRequest){

        User user=User.builder()
        // .userName(userRequest.getUserName())
        // .password(encoder.encode(userRequest.getPassword()))
        // .roles(userRequest.getRole())
        .build();
        Set<Role> roles=new HashSet<>();

        for(String role:userRequest.getRole()){
            roles.add(roleService.findByRole(role));
            System.out.println(roles);
        }

        user.setPassword(encoder.encode(userRequest.getPassword()));
        System.out.println(user.getPassword());
        User new_user=User
        .builder()
        .userName(userRequest.getUserName())
        .password(user.getPassword())
        .roles(roles)
        // .profileImage(user.getProfileImage())
        .build();
        return userRepo.save(new_user);
    }


    public List<User> getAllUser(){
        return userRepo.findAll();
    }


    public ResponseEntity<?> updateUser(Integer id,MultipartFile image){
        try{
            User user=userRepo.findById(id).orElse(null);
            user.setProfileImage(image.getBytes());
            userRepo.save(user);
           return ResponseEntity.status(HttpStatus.OK).body(userRepo.findById(id).orElse(null));
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userRepo.findById(id).orElse(null));
    }
}
