package com.backend.backend.dto;

public record ComplaintRequest(String title,
    String description,
    String user,
    Integer room
) {

} 