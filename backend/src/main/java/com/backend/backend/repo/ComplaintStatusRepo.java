package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.ComplaintStatus;
@Repository
public interface ComplaintStatusRepo extends JpaRepository<ComplaintStatus,Integer>{

}
