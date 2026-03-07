package com.backend.backend.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotEmpty(message = "usename must be required")
    private String userName;
    @NotEmpty(message = "usename must be required")
    @Size(max = 14,min = 4,message = "max 14 character and min 4 character")
    private String password;
    // @NotBlank(message = "At least one role is required")
    @NotEmpty
    private List<String> role;
}
