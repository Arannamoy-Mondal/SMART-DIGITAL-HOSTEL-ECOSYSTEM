package com.backend.backend.controller;

import java.time.temporal.ChronoUnit;
import java.util.List;

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

import com.backend.backend.dto.RoomBookingRequest;

import com.backend.backend.service.RoomBookingService;

@RestController
@RequestMapping("/roomBooking")
public class RoomBookingController {

    @Autowired
    private RoomBookingService roomBookingService;

    @PostMapping("/create")
    public ResponseEntity<?> createRoomBooking(
            @RequestBody RoomBookingRequest request) {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.createRoomBooking(request));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAllRoomBooking(

    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAllRoomBooking());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get/userName/{userName}")
    public ResponseEntity<?> getRoomBookingByUserName(
            @PathVariable("userName") String userName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getRoomBookingByUserName(userName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
