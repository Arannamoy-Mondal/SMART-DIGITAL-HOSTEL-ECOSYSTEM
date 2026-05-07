package com.backend.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.model.Complaint;
import com.backend.backend.model.User;

public interface ComplaintRepo extends JpaRepository<Complaint,Integer>{
    List<Complaint> findByUser(User user);
}
