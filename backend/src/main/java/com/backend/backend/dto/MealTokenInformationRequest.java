package com.backend.backend.dto;

import java.math.BigDecimal;

public record MealTokenInformationRequest(
        String userName,
        Integer roomNo,
        Integer tokenAmount,
        BigDecimal amount,
        String paymentMethod) {
}
