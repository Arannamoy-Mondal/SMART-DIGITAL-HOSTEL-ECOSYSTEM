package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.backend.dto.LoginRequest;
import com.backend.backend.dto.UserRequest;

import com.backend.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
   

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.signup(userRequest));
           
            
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid@RequestBody LoginRequest loginRequest,
        BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage());
        }
            try {
                return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginRequest));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }


    @PostMapping(value = "/upload-image/{userId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)  
    public ResponseEntity<?> uploadImage(
        @PathVariable("userId") Integer userId,
        @RequestParam("image") MultipartFile image
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, image));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }  
}
