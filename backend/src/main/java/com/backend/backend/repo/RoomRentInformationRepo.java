package com.backend.backend.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.Room;
import com.backend.backend.model.RoomRentInformation;
import com.backend.backend.model.User;
@Repository
public interface RoomRentInformationRepo extends JpaRepository<RoomRentInformation,Integer> {
    List<RoomRentInformation> findByUser(User user);
    List<RoomRentInformation> findByEndDateBeforeAndIsActiveTrue(LocalDate date);
    List<RoomRentInformation> findByUserAndIsActiveTrue(User user);
    List<RoomRentInformation> findByRoomAndIsActiveTrue(Room room);
}
