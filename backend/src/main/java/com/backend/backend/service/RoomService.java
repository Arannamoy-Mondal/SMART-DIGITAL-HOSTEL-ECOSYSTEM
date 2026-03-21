package com.backend.backend.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.RoomRequest;
import com.backend.backend.model.Floor;
import com.backend.backend.model.Room;
import com.backend.backend.model.RoomType;
import com.backend.backend.repo.FloorRepo;
import com.backend.backend.repo.RoomRepo;
import com.backend.backend.repo.RoomTypeRepo;

@Service
public class RoomService {
    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private RoomTypeRepo roomTypeRepo;

    @Autowired
    private FloorRepo floorRepo;

    public ResponseEntity<?> createRoom(RoomRequest roomRequest) {
        try {

            RoomType roomType = roomTypeRepo.findByRoomType(roomRequest.getRoomType()).orElse(null);
            Floor floor = floorRepo.findByFloorNo(roomRequest.getFloorNo()).orElse(null);
            Room res = roomRepo.findByRoomNo(roomRequest.getRoomNo()).orElse(null);
            if (res != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room already exist with room no "
                        + roomRequest.getRoomNo() + ". Please try with different room no.");
            }
            // if (floor == null) {
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Floor not found with
            // floor " + roomRequest.getFloorNo() + "Please try other or contact with
            // admininstrator.");
            // }
            // if (roomType == null) {
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room type not found
            // with room type " + roomRequest.getRoomType() + "Please try other or contact
            // with admininstrator.");
            // }
            Room room = Room.builder()
                    .roomNo(roomRequest.getRoomNo())
                    .roomType(roomType)
                    .floor(floor)
                    .perDayRentFee(roomRequest.getPerDayRentFee())
                    .build();
            Room savedRoom = roomRepo.save(room);
            return ResponseEntity.status(HttpStatus.OK).body(savedRoom);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getRooms() {
        try {
            List<Room> rooms = roomRepo.findAll();
            if (rooms.size() < 1) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rooms found. Please try again later.");
            }
            return ResponseEntity.status(HttpStatus.FOUND).body(rooms);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> updateRoom(Integer id, RoomRequest entity) {
        try {
            Room room = roomRepo.findById(id).orElse(null);
            Floor floor = floorRepo.findByFloorNo(entity.getFloorNo()).orElse(null);
            RoomType roomType = roomTypeRepo.findByRoomType(entity.getRoomType()).orElse(null);
            if (room == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room found with id " + id);
            }
            room.setRoomNo(entity.getRoomNo());
            room.setRoomType(roomType);
            room.setFloor(floor);
            room.setPerDayRentFee(entity.getPerDayRentFee());
            roomRepo.save(room);

            return ResponseEntity.status(HttpStatus.OK).body(roomRepo.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getRoomById(Integer id) {
        try {
            Room room = roomRepo.findById(id).orElse(null);
            if (room == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room found with id " + id);
            }
            return ResponseEntity.status(HttpStatus.FOUND).body(room);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getRoomByRoomNo(Integer roomNo) {
        try {
            Room room = roomRepo.findByRoomNo(roomNo).orElse(null);
            if (room == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room found with no " + roomNo);
            }
            return ResponseEntity.status(HttpStatus.FOUND).body(room);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getRoomByRoomType(String entity) {
        try {
            RoomType roomType = roomTypeRepo.findByRoomType(entity).orElse(null);
            if (roomType == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room type found with room type " + entity);
            }
            List<Room> rooms = roomRepo.findAllByRoomType(roomType);
            if (rooms.size() < 1) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room found with room type " + entity);
            }
            return ResponseEntity.status(HttpStatus.FOUND).body(rooms);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteRoom(Integer roomId) {
        try {
            Room room = roomRepo.findById(roomId).orElse(null);
            if (room == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room found with roomId " + roomId);
            }
            roomRepo.deleteById(roomId);
            return ResponseEntity.status(HttpStatus.OK).body("RoomType deleted successfully with id "+roomId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
