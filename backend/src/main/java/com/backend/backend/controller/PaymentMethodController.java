package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.service.PaymentMethodService;

@RestController
@RequestMapping("/payment-method")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping("/get")
    public ResponseEntity<?> getPaymentMethod() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(paymentMethodService.getPaymentMethod());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    @GetMapping("/get/{paymentMethod}")
    public ResponseEntity<?> getPaymentMethodByPaymentMethod(
        @PathVariable("paymentMethod") String paymentMethod
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(paymentMethodService.getPaymentMethodByPaymentMethod(paymentMethod));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
