package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.backend.dto.UserRequest;
import com.backend.backend.jwt.JwtService;
import com.backend.backend.model.User;
import com.backend.backend.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {
    @Autowired
    public UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserRequest userRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("request not pass");
            System.out.println(bindingResult.getErrorCount());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        try {
            System.out.println("request pass");
            userService.saveUser(userRequest);
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());

        } catch (Exception e) {
            // System.out.println(e.getMessage());
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // System.out.println(user.getUserName()+" "+user.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        String result = "";
        if (authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.OK).body(jwtService.generateToken(user.getUserName()));
        } else {
            result = "Failed";
        }
        // System.out.println(user.getUserName());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(value = "/upload-image/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadUserImage(@PathVariable("id") Integer id,
            @RequestParam("image") MultipartFile image) {
        // return ResponseEntity.status(0);
        // log.info(id);
        try {

            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, image));
        } catch (Exception e) {
            // System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // test purpose only
    @GetMapping("/all")
    public ResponseEntity<Object> getMethodName() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
