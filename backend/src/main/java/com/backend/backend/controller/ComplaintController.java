package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.ComplaintRequest;
import com.backend.backend.service.ComplaintService;


@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllComplaint() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(complaintService.getAllComplaint());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/create")
    public ResponseEntity<?> createComplaint(
            @RequestBody ComplaintRequest complaintRequest) {
        try {
 
            return ResponseEntity.status(HttpStatus.OK).body(complaintService.createComplaint(complaintRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get/userName/{username}")
    public ResponseEntity<?> getComplaintByUserName(
            @PathVariable("username") String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(complaintService.getComplaintByUserName(username));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComplaint(
            @PathVariable("id") Integer id,
            @RequestParam("status") String status) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(complaintService.updateComplaint(id, status));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
