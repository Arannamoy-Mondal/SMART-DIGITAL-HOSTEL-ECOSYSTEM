package com.backend.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.FloorRequest;
import com.backend.backend.model.Floor;
import com.backend.backend.repo.FloorRepo;

@Service
public class FloorService {
    @Autowired
    private FloorRepo floorRepo;

    public ResponseEntity<?> create(
            FloorRequest floorRequest) {
        try {
            Floor floor = Floor.builder()
                    .floorNo(floorRequest.getFloorNo())
                    .build();
            var savedFloor = floorRepo.save(floor);
            return ResponseEntity.status(HttpStatus.OK).body(savedFloor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getFloors() {
        try {
            List<Floor> floors = floorRepo.findAll();
            if (floors.size() < 1) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Floor Found");
            }
            return ResponseEntity.status(HttpStatus.FOUND).body(floors);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getFloorById(Integer floorId) {
        try {
            var floor = floorRepo.findById(floorId).orElse(null);
            if (floor == null) {
                return ResponseEntity.status(HttpStatus.OK).body("Floor not found with id " + floorId);
            }
            return ResponseEntity.status(HttpStatus.OK).body(floor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteFloor(Integer floorId) {
        try {

            var floor = floorRepo.findById(floorId).orElse(null);
            if (floor == null) {
                return ResponseEntity.status(HttpStatus.OK).body("Floor not found with id " + floorId);
            }
            // var deletedFloor=
            floorRepo.deleteById(floorId);
            return ResponseEntity.status(HttpStatus.OK).body("Floor deleted successfully with id " + floorId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
