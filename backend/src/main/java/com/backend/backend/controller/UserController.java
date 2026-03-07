package com.backend.backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.backend.dto.UserRequest;
import com.backend.backend.jwt.JwtService;
import com.backend.backend.model.User;
import com.backend.backend.service.UserService;

import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> register(
            @RequestBody UserRequest userRequest
            // ,@RequestParam("image") MultipartFile image
        ) {
        try {
            // ObjectMapper objectMapper = new ObjectMapper();
            // User user1 = objectMapper.readValue(user0, User.class);

            System.out.println(userRequest);
            userService.saveUser(userRequest);
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        System.out.println(user.getUserName()+" "+user.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        String result = "";
        if (authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.OK).body(jwtService.generateToken(user.getUserName()));
        } else {
            result = "Failed";
        }
        System.out.println(user.getUserName());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @PostMapping(value = "/upload-image/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update_user(@PathVariable("id") Integer id,@RequestParam("image") MultipartFile image) {
        return userService.updateUser(id,image);
    }
    
}
