package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.TransactionRequest;
import com.backend.backend.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(
            @RequestBody TransactionRequest transactionRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.createTransaction(transactionRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAllTransaction(

    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.getAllTransaction());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get/userName/{userName}")
    public ResponseEntity<?> getTransactionByUserName(
            @PathVariable("userName") String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.getTransactionByUserName(username));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get/roomNo/{roomNo}")
    public ResponseEntity<?> getTransactionByRoomNo(
            @PathVariable("roomNo") Integer roomNo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.getTransactionByRoomNo(roomNo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
