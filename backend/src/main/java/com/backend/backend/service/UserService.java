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



    public Object updateProfile(String userName, UserRequest req) throws Exception {
        User user = userRepo.findByUserName(userName).orElseThrow(() -> new Exception("User not found"));
        
        if (req.getFirstName() != null && !req.getFirstName().isEmpty()) user.setFirstName(req.getFirstName());
        if (req.getLastName() != null && !req.getLastName().isEmpty()) user.setLastName(req.getLastName());
        if (req.getEmail() != null && !req.getEmail().isEmpty()) user.setEmail(req.getEmail());
        if (req.getContactNo() != null && !req.getContactNo().isEmpty()) user.setContactNo(req.getContactNo());
        if (req.getPermanentAddress() != null && !req.getPermanentAddress().isEmpty()) user.setPermanentAddress(req.getPermanentAddress());
        
        userRepo.save(user);
        return "Profile updated successfully";
    }

    
    public Object changeRole(Integer userId, String roleName) throws Exception {
        User user = userRepo.findById(userId).orElseThrow(() -> new Exception("User not found"));
        Role role = roleRepo.findByRole(roleName).orElseThrow(() -> new Exception("Role not found: " + roleName));
        user.setRole(role);
        userRepo.save(user);
        return "User role successfully changed to " + roleName;
    }



    public Object toggleBlock(Integer userId) throws Exception {
        User user = userRepo.findById(userId).orElseThrow(() -> new Exception("User not found"));
        if ("admin".equalsIgnoreCase(user.getUserName()) || 
            (user.getRole() != null && "admin".equalsIgnoreCase(user.getRole().getRole()))) {
            throw new Exception("Admin cannot be blocked!");
        }

        Boolean isBlocked = user.getBlocked();
        user.setBlocked(isBlocked == null ? true : !isBlocked); 
        userRepo.save(user);
        return "User block status updated!";
    }

}
