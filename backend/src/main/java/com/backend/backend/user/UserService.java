package com.backend.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    public User saveUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        User new_user=User
        .builder()
        .userName(user.getUserName())
        .password(user.getPassword())
        .build();
        return userRepo.save(new_user);
    }
}
