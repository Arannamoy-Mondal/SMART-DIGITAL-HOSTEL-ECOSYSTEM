package com.backend.backend.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.Refund;

@Repository
public interface RefundRepo extends JpaRepository<Refund,Integer>{
    List<Refund> findByRoomNo(Integer roomNo);
    List<Refund> findByUserName(String userName);    
} 
