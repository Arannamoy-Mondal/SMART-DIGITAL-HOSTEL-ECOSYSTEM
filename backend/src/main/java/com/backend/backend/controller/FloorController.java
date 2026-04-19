package com.backend.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.FloorRequest;
import com.backend.backend.service.FloorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/floor")
public class FloorController {

    @Autowired
    private FloorService floorService;
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createFloor(@RequestBody FloorRequest entity) {
        return floorService.create(entity);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getFloors() {
        return floorService.getFloors();
    }

    @GetMapping("/get/floorNo/{floorNo}")
    public ResponseEntity<?> getFloorById(@PathVariable("floorNo") Integer floorNo) {
        return floorService.getFloorById(floorNo);
    }
    

    @DeleteMapping("/delete/{floorId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteFloor(@PathVariable("floorId") Integer floorId){
        return floorService.deleteFloor(floorId);
    }
    
}
