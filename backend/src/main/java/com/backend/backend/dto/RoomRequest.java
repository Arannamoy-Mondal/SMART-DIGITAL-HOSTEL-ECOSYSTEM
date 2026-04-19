package com.backend.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {
    private Integer roomNo;
    private String roomType;
    private Integer floorNo;
    private Float perDayRentFee;
}
