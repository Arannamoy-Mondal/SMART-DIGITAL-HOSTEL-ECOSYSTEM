package com.backend.backend.service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.model.PaymentPurpose;
import com.backend.backend.repo.PaymentPurposeRepo;

@Service
public class PaymentPurposeService {

    @Autowired
    private PaymentPurposeRepo paymentPurposeRepo;

    public @Nullable Object getPaymentPurpose() throws Exception{
      try {
        return paymentPurposeRepo.findAll();
      } catch (Exception e) {
        // TODO: handle exception
        throw new Exception(e.getMessage());
      }
    }

    public @Nullable Object getPaymentPurposeByPaymentPurpose(String paymentPurpose) throws Exception {
        try {
        PaymentPurpose tmp=paymentPurposeRepo.findByPaymentPurpose(paymentPurpose.toLowerCase())
        .orElseThrow(()->new Exception("No data found with payment purpose: "+paymentPurpose));
        return tmp;
      } catch (Exception e) {
        // TODO: handle exception
        throw new Exception(e.getMessage());
      }
    }

    public @Nullable Object getPaymentPurposeByPaymentPurposeById(Integer paymentPurposeId) throws Exception {
        try {
        PaymentPurpose tmp=paymentPurposeRepo.findById(paymentPurposeId)
        .orElseThrow(()->new Exception("No data found with payment purpose: "+paymentPurposeId));
        return tmp;
      } catch (Exception e) {
        // TODO: handle exception
        throw new Exception(e.getMessage());
      }
    }

  

}
