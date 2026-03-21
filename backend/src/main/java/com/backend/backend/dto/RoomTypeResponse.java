package com.backend.backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomTypeResponse {
    private Integer roomTypeId;
  
    private String roomType;

    private LocalDateTime createdAt;

    private String createdBy;
}
