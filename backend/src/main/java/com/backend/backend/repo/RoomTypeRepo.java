package com.backend.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.RoomType;



@Repository
public interface RoomTypeRepo extends JpaRepository<RoomType,Integer>{
    Optional<?> findByRoomType(String roomType);
}
