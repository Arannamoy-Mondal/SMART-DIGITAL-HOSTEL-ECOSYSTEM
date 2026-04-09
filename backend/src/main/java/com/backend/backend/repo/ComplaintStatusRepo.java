package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.model.ComplaintStatus;

public interface ComplaintStatusRepo extends JpaRepository<Integer,ComplaintStatus>{

}
