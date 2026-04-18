package com.backend.backend.dto;

import java.time.LocalDate;
import java.util.List;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotEmpty(message = "usename must be required")
    @Size(max = 7,min = 3,message = "username size max 7 characters and min 3 characters")
    private String userName;
    @NotEmpty(message = "first name must be required")
    private String firstName;
    @NotEmpty(message = "last name must be required")
    private String lastName;
    @NotEmpty(message = "email must be required")
    private String email;
    @NotEmpty(message = "contact number must be required")
    private String contactNo;
    @NotEmpty(message = "emergency contact no must be required")
    private String emergencyContactNo;
    
    private LocalDate birthDate;
    @NotEmpty(message = "permenant address must be required")
    private String permanentAddress;
    private String passportId;
    @NotEmpty(message = "password must be required")
    @Size(max = 14,min = 4,message = "password size max 14 characters and min 4 character")
    private String password;
    // @NotEmpty(message = "At least one role.")
    private String role;
}
