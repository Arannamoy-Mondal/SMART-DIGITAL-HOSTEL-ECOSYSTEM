package com.backend.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer refundId;
    private String userName;
    private Integer roomNo;
    private Integer amount;
    private Integer transactionId;
}
