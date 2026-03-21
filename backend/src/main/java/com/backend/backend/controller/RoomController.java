package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createRoom(@RequestBody RoomRequest entity) {
        return roomService.createRoom(entity);
    }
    

    @GetMapping("")
    public ResponseEntity<?> getRooms(@RequestParam(required = false) String param) {
        return roomService.getRooms();
    }

    @GetMapping("/roomId/{roomId}")
    public  ResponseEntity<?> getRoomById(@PathVariable("roomId") Integer roomId) {
        return roomService.getRoomById(roomId);
    }
    
    @GetMapping("/roomNo/{roomNo}")
    public ResponseEntity<?> getRoomByRoomNo(@PathVariable("roomNo") Integer roomNo) {
        return roomService.getRoomByRoomNo(roomNo);
    }


    @GetMapping("/roomType")
    public ResponseEntity<?> getRoomByRoomType(@RequestParam("roomType") String roomType) {
        System.out.println(roomType);
        return roomService.getRoomByRoomType(roomType);
    }
    

    @PutMapping("/update/{roomId}")
    public ResponseEntity<?> updateRoom(@PathVariable("roomId") Integer roomId, @RequestBody RoomRequest entity) {
        return roomService.updateRoom(roomId,entity);
    }
    
    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable("roomId") Integer roomId) {
        return roomService.deleteRoom(roomId);
    }
    
}
