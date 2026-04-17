package com.backend.backend.dto;

import java.time.LocalDate;

public record RoomRentInformationRequest(
    Integer roomRentDays,
    LocalDate startDate,
    LocalDate endDate,
    boolean mealStatus,
    Integer totalMealCount,
    String userName,
    Integer roomNo
) {

}
