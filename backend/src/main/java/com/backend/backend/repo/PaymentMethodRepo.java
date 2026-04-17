package com.backend.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.PaymentMethod;
@Repository
public interface PaymentMethodRepo extends JpaRepository<PaymentMethod,Integer>{
    Optional<PaymentMethod> findByPaymentMethod(String paymentMethod);
}
