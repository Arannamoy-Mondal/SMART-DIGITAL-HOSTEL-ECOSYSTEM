package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.Floor;

@Repository
public interface FloorRepo extends JpaRepository<Floor,Integer>{
    Floor findByFloorNo(int floorNo);
}
