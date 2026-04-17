package com.backend.backend.dto;

import java.util.List;



public record MenuRequest(
    String day,
    String mealType,
    List<String> menuItems
){

}
