package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.model.Review;

public interface ReviewRepo extends JpaRepository<Integer,Review>{

}
