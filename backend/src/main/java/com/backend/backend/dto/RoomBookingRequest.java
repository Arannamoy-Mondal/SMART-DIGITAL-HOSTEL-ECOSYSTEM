package com.backend.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RoomBookingRequest(
    String userName,
    Integer roomNo,
    LocalDate startDate,
    LocalDate endDate,
    BigDecimal amount,
    String paymentMethod
) {

}
