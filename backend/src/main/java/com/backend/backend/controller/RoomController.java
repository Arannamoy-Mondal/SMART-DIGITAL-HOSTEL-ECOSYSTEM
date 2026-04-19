package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.RoomRequest;
import com.backend.backend.service.RoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createRoom(@RequestBody RoomRequest entity) {
        return roomService.createRoom(entity);
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getRooms(@RequestParam(required = false) String param) {
        return roomService.getRooms();
    }

    @GetMapping("/get/roomId/{roomId}")
    public ResponseEntity<?> getRoomById(@PathVariable("roomId") Integer roomId) {
        return roomService.getRoomById(roomId);
    }

    @GetMapping("/get/roomNo/{roomNo}")
    public ResponseEntity<?> getRoomByRoomNo(@PathVariable("roomNo") Integer roomNo) {
        return roomService.getRoomByRoomNo(roomNo);
    }

    @GetMapping("/get/roomType/{roomType}")
    public ResponseEntity<?> getRoomByRoomType(@PathVariable("roomType") String roomType) {
        System.out.println(roomType);
        return roomService.getRoomByRoomType(roomType);
    }

    @PutMapping("/update/{roomNo}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateRoom(@PathVariable("roomNo") Integer roomNo, @RequestBody RoomRequest entity) {
        return roomService.updateRoom(roomNo, entity);
    }

    @DeleteMapping("/delete/{roomId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteRoom(@PathVariable("roomId") Integer roomId) {
        return roomService.deleteRoom(roomId);
    }

}
