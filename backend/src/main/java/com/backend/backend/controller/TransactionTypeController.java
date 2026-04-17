package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.TransactionTypeRequest;
import com.backend.backend.model.TransactionType;
import com.backend.backend.service.TransactionTypeService;

@RestController
@RequestMapping("/transaction-type")
public class TransactionTypeController {

    @Autowired
    private TransactionTypeService transactionTypeService;

    // @PostMapping("/create")
    // public ResponseEntity<?> createTransactionType(
    // @RequestBody TransactionTypeRequest transactionTypeRequest
    // ) {
    // try {
    // return
    // ResponseEntity.status(HttpStatus.OK).body(transactionTypeService.createTransactionType(transactionTypeRequest));
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    // }

    // }

    @GetMapping("/get")
    public ResponseEntity<?> getTransactionType() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionTypeService.getTransactionType());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get/{transactionType}")
    public ResponseEntity<?> getTransactionTypeByTransactionType(
        @PathVariable("transactionType") String transactionType
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionTypeService.getTransactionTypeByTransactionType(transactionType));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
