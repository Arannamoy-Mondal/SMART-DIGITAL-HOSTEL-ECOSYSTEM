package com.backend.backend.service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.model.PaymentMethod;
import com.backend.backend.repo.PaymentMethodRepo;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    public @Nullable Object getPaymentMethod() throws Exception {
        try {
            return paymentMethodRepo.findAll();
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object getPaymentMethodByPaymentMethod(String paymentMethod) throws Exception {
       try {
            PaymentMethod tmp=paymentMethodRepo.findByPaymentMethod(paymentMethod.toLowerCase())
            .orElseThrow(()->new Exception("No payment method found with payment method: "+paymentMethod));
            return tmp;
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.getMessage());
        }
    }

    
}
