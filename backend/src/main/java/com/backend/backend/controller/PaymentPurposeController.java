package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.service.PaymentPurposeService;

@RestController
@RequestMapping("/payment-purpose")
public class PaymentPurposeController {

    @Autowired
    public PaymentPurposeService paymentPurposeService;

    @GetMapping("/get")
    public ResponseEntity<?> getPaymentPurpose() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(paymentPurposeService.getPaymentPurpose());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    // @GetMapping("/get/paymentPurpose/{paymentPurpose}")
    // public ResponseEntity<?> getPaymentPurposeByPaymentPurpose(
    //     @PathVariable("paymentPurpose") String paymentPurpose
    //             ) {
    //         try {
    //             return ResponseEntity.status(HttpStatus.OK).body(paymentPurposeService.getPaymentPurposeByPaymentPurpose(paymentPurpose));
    //         } catch (Exception e) {
    //             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    //         }
    
    //     }


    @GetMapping("/get/{paymentPurposeId}")
    public ResponseEntity<?> getPaymentPurposeByPaymentPurposeId(
        @PathVariable("paymentPurposeId") Integer paymentPurposeId
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(paymentPurposeService.getPaymentPurposeByPaymentPurposeById(paymentPurposeId));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }    
}
