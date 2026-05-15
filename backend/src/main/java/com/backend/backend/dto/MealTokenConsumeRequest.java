package com.backend.backend.dto;

public record MealTokenConsumeRequest(
        String userName,
        Integer deductAmount,
        String mealType
) {

}
