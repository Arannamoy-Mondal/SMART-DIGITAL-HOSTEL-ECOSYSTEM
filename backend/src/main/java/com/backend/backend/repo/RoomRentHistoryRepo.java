package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.RoomRentHistory;
@Repository
public interface RoomRentHistoryRepo extends JpaRepository<RoomRentHistory,Integer>{

}
