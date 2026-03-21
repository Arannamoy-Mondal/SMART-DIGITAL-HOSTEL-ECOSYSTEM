package com.backend.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {
    private int roomNo;
    private String roomType;
    private int floorNo;
    private float perDayRentFee;
}
