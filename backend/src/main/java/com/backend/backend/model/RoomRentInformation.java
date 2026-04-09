package com.backend.backend.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RoomRentInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomRentInformationId;
    private int roomRentDays;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean mealStatus;
    private int totalMealCount;
    private int usedMealCount;
    private int lifetimeUsedMealToken;


    private List<User> users;
    // private Room room;
}
