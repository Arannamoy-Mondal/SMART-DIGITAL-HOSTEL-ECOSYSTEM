package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.PaymentPurpose;
@Repository
public interface PaymentPurposeRepo extends JpaRepository<PaymentPurpose,Integer>{

}
