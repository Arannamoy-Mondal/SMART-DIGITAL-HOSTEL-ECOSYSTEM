package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.RoomRentInformation;
@Repository
public interface RoomRentInformationRepo extends JpaRepository<RoomRentInformation,Integer> {

}
