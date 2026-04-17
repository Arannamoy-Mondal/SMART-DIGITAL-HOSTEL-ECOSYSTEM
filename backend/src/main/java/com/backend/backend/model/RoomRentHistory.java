package com.backend.backend.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomRentHistoryId;

    // private List<User> users;

}

/* unnecessary */