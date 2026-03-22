package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.model.PaymentPurpose;

public interface PaymentPurposeRepo extends JpaRepository<PaymentPurpose,Integer>{

}
