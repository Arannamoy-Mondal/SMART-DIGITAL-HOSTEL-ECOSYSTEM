package com.backend.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {
    @NotEmpty(message = "username required")
    private String userName;
    @NotEmpty(message = "password required")
    private String password;
}
