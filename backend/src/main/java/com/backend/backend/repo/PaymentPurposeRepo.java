package com.backend.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.PaymentPurpose;
@Repository
public interface PaymentPurposeRepo extends JpaRepository<PaymentPurpose,Integer>{
    Optional<PaymentPurpose> findByPaymentPurpose(String paymentPurpose);
}
