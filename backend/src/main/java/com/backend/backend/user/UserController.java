package com.backend.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        System.out.println(user.getUserName());
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        Authentication authentication=authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword())
        );
        String result="";
        if (authentication.isAuthenticated()){
            return ResponseEntity.status(HttpStatus.OK).body(jwtService.generateToken(user.getUserName()));
        }
        else{
            result="Failed";
        }
        System.out.println(user.getUserName());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
