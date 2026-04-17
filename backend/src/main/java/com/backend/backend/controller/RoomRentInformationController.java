package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.service.RoomRentInformationService;

@RestController
@RequestMapping("/room-rent-information")
public class RoomRentInformationController {
    @Autowired
    private RoomRentInformationService roomRentInformationService;

    @GetMapping("/get")
    public ResponseEntity<?> getRoomRentInformation() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(roomRentInformationService.getRoomRentInformation());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get/{userName}")
    public ResponseEntity<?> getRoomInformationByUserName(
            @PathVariable("userName") String userName) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(roomRentInformationService.getRoomInformationByUserName(userName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
