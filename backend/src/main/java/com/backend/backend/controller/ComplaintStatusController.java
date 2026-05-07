package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.repo.ComplaintStatusRepo;

@RestController
@RequestMapping("/complaintStatus")
public class ComplaintStatusController {
    @Autowired
    private ComplaintStatusRepo complaintStatusRepo;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllStatus() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(complaintStatusRepo.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
