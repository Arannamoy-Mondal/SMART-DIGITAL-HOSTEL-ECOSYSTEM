package com.backend.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.LeaveApplication;

@Repository
public interface LeaveApplicationRepo extends JpaRepository<LeaveApplication,Integer> {
    List<LeaveApplication> findByUserName(String userName);
    List<LeaveApplication> findByRoomNo(Integer roomNo);

}
