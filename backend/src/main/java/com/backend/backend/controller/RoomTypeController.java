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

import com.backend.backend.dto.RoomTypeRequest;
import com.backend.backend.service.RoomTypeService;

@RestController
@RequestMapping("/room-type")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;

    @PostMapping("/create")
    public ResponseEntity<?> createRoomType(
            @RequestBody RoomTypeRequest roomTypeRequest) {
                
        return roomTypeService.createRoomType(roomTypeRequest);
    }

    @GetMapping("")
    public ResponseEntity<?> getRoomTypes() {

            return roomTypeService.getRoomTypes();
       
    }

    @GetMapping("/{roomTypeId}")
    public ResponseEntity<?> getRoomTypeById(
            @PathVariable("roomTypeId") Integer roomTypeId) {
        return roomTypeService.getRoomTypeById(roomTypeId);

    }
}
