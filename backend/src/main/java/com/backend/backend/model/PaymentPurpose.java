package com.backend.backend.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class PaymentPurpose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentPurposeId;
    private String paymentPurpose;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;

    @OneToMany(mappedBy = "paymentPurpose")
    @JsonBackReference
    @ToString.Exclude
    private List<Transaction> transactions;
}
