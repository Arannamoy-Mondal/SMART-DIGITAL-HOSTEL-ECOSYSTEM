package com.backend.backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.RoomTypeRequest;
import com.backend.backend.mapper.RoomTypeMapper;
import com.backend.backend.model.RoomType;
import com.backend.backend.repo.RoomTypeRepo;

@Service
public class RoomTypeService {
    @Autowired
    private RoomTypeRepo roomTypeRepo;

    public ResponseEntity<?> createRoomType(RoomTypeRequest roomTypeRequest) {
        try {
            RoomType roomType = RoomTypeMapper.mapToRoomType(roomTypeRequest);
            roomType.setRoomType(roomType.getRoomType().toLowerCase());
            // if (roomTypeRepo.findByRoomType(roomType.getRoomType().toLowerCase()) != null) {
            //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("room type already exist");
            // }
            var savedUser = roomTypeRepo.save(roomType);
            return ResponseEntity.status(HttpStatus.OK).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong please try again letter.");
        }
    }

    public ResponseEntity<?> getRoomTypes() {
        try {
            var roomTypes = roomTypeRepo.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(roomTypes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getRoomTypeById(Integer roomTypeId) {
        try {
            var roomType = roomTypeRepo.findById(roomTypeId).orElse(null);
            if (roomType == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room type found with id " + roomTypeId);
            }
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    roomType);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    public ResponseEntity<?> getRoomTypeByRoomType(String roomType){
            try {

                RoomType res=roomTypeRepo.findByRoomType(roomType).orElse(null);
                System.out.println(res);
                if(res==null){
                    return ResponseEntity.status(HttpStatus.OK).body("No room type found with this "+roomType);
                }
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }

    public ResponseEntity<?> updateRoomType(Integer roomTypeId, RoomTypeRequest roomTypeRequest) {
        try {
            var roomType = roomTypeRepo.findById(roomTypeId).orElse(null);
            if (roomType == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room type found with id " + roomTypeId);
            }
            var updatedRoomType=RoomTypeMapper.mapToRoomType(roomTypeRequest);
            roomType.setRoomType(updatedRoomType.getRoomType());
            var res=roomTypeRepo.save(roomType);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteRoomTypeById(Integer roomTypeId) {
        try {
            var deletedRoomType = roomTypeRepo.findById(roomTypeId).orElse(null);
            if (deletedRoomType != null) {
                roomTypeRepo.deleteById(roomTypeId);
                return ResponseEntity.status(HttpStatus.OK).body("RoomType deleted successfully with id "+roomTypeId);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("RoomType not found with id "+roomTypeId);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
