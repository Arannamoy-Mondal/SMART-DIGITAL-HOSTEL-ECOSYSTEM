package com.backend.backend.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    @JsonManagedReference
    @ToString.Exclude
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @ToString.Exclude
    private TransactionType transactionType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @ToString.Exclude
    private PaymentMethod paymentMethod;




    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private PaymentPurpose paymentPurpose;


    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;




}
