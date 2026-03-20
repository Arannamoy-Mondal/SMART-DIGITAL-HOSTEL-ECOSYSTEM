package com.backend.backend.dto;

import java.util.List;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotEmpty(message = "usename must be required")
    @Size(max = 7,min = 3,message = "username size max 7 characters and min 3 characters")
    private String userName;
    @NotEmpty(message = "password must be required")
    @Size(max = 14,min = 4,message = "password size max 14 characters and min 4 character")
    private String password;
    // @NotEmpty(message = "At least one role.")
    private List<String> role;
}
