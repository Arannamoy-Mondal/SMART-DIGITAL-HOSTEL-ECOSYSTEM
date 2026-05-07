package com.backend.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.backend.authentication.service.AuthenticationService;
import com.backend.backend.dto.LoginRequest;
import com.backend.backend.dto.UserRequest;
import com.backend.backend.mapper.UserMapper;
import com.backend.backend.model.Role;
import com.backend.backend.model.User;
import com.backend.backend.repo.RoleRepo;
import com.backend.backend.repo.UserRepo;

import jakarta.annotation.Nullable;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepo roleRepo;
    public @Nullable Object signup(UserRequest userRequest) throws Exception {

        try {
            Role role=roleRepo.findByRole(userRequest.getRole()).orElseThrow(
                ()->new Exception("role not found: "+userRequest.getRole())
            );
            User user = User.builder()
            .userName(userRequest.getUserName())
            .firstName(userRequest.getFirstName())
            .lastName(userRequest.getLastName())
            .contactNo(userRequest.getContactNo())
            .emergencyContactNo(userRequest.getEmergencyContactNo())
            .permanentAddress(userRequest.getPermanentAddress())
            .email(userRequest.getEmail())
            .role(role)
            .birthDate(userRequest.getBirthDate())
            .passportId(userRequest.getPassportId())
            .build();
            if (userRepo.findByUserName(user.getUserName()).orElse(null) != null) {
                throw new Exception("username already exist");
            }
            user.setPassword(encoder.encode(userRequest.getPassword()));
            System.out.println(user);
            User savedUser = userRepo.save(user);
            return "Signup successfully.";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<User> getUsers() throws Exception {
        try {
            List<User> users = userRepo.findAll();
            return users;
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.getMessage());
        }
    }

    public User updateUser(Integer id, MultipartFile image) throws Exception {
        try {
            User user = userRepo.findById(id).orElse(null);
            user.setProfileImage(image.getBytes());
            userRepo.save(user);
            return userRepo.findById(user.getUserId()).orElse(null);
            // return
            // ResponseEntity.status(HttpStatus.OK).body(userRepo.findById(id).orElse(null));
        } catch (Exception e) {
            // log.info(e.getMessage());
            throw new Exception("Image not successful.");
        }
        // return
        // ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userRepo.findById(id).orElse(null));
    }

    public @Nullable Object login(LoginRequest loginRequest) throws Exception {
        User user=userRepo.findByUserName(loginRequest.getUserName())
        .orElseThrow(()->new Exception("username not found."));
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUserName(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return authenticationService.createJwtToken(authentication);

    }

	public @Nullable Object getUserByUserName(String userName) throws Exception {
		User user=userRepo.findByUserName(userName).orElseThrow(()->new Exception("username not found."));
        return user;
	}


    

}
