package com.backend.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.RoomRentInformation;
import com.backend.backend.model.User;
@Repository
public interface RoomRentInformationRepo extends JpaRepository<RoomRentInformation,Integer> {
    List<RoomRentInformation> findByUser(User user);
}
