package com.backend.backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.Room;
import com.backend.backend.model.RoomType;

@Repository
public interface RoomRepo extends JpaRepository<Room,Integer>{
    Optional<Room> findByRoomNo(int roomNo);
    List<Room> findAllByRoomType(RoomType roomType);
}
