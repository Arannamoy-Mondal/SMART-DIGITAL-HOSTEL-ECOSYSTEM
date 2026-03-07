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

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserService {
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private RoleService roleService;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    public User saveUser(UserRequest userRequest) throws Exception{

        try {
            User user=User.builder()
        // .userName(userRequest.getUserName())
        // .password(encoder.encode(userRequest.getPassword()))
        // .roles(userRequest.getRole())
        .build();
        Set<Role> roles=new HashSet<>();

        for(String role:userRequest.getRole()){
            roles.add(roleService.findByRole(role));
            // System.out.println(roles);
        }

        user.setPassword(encoder.encode(userRequest.getPassword()));
        // System.out.println(user.getPassword());
        User new_user=User
        .builder()
        .userName(userRequest.getUserName())
        .password(user.getPassword())
        .roles(roles)
        // .profileImage(user.getProfileImage())
        .build();
        return userRepo.save(new_user);
        } catch (Exception e) {
            throw new Exception("User signup failed. Please try later.");
        }
    }


    public List<User> getAllUser() throws Exception{
        try {
            List<User> users=userRepo.findAll();
            if(users.size()>0){
                return users;
            }
            throw new Exception("Please try later.");
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception("Please try later.");
        }
    }


    public User updateUser(Integer id,MultipartFile image) throws Exception{
        try{
            User user=userRepo.findById(id).orElse(null);
            user.setProfileImage(image.getBytes());
            userRepo.save(user);
            return userRepo.findById(user.getId()).orElse(null);
        //    return ResponseEntity.status(HttpStatus.OK).body(userRepo.findById(id).orElse(null));
        } catch (Exception e) {
        //   log.info(e.getMessage());
          throw new Exception("Image not successful.");
        }
        // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userRepo.findById(id).orElse(null));
    }
}
