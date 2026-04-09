package com.backend.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.FloorRequest;
import com.backend.backend.service.FloorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createFloor(@RequestBody FloorRequest entity) {
        return floorService.create(entity);
    }

    @GetMapping("")
    public ResponseEntity<?> getFloors() {
        return floorService.getFloors();
    }

    @GetMapping("/{floorId}")
    public ResponseEntity<?> getFloorById(@PathVariable("floorId") Integer floorId) {
        return floorService.getFloorById(floorId);
    }
    

    @DeleteMapping("/delete/{floorId}")
    public ResponseEntity<?> deleteFloor(@PathVariable("floorId") Integer floorId){
        return floorService.deleteFloor(floorId);
    }
    
}
