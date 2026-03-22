package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.model.PaymentMethod;

public interface PaymentMethodRepo extends JpaRepository<PaymentMethod,Integer>{

}
