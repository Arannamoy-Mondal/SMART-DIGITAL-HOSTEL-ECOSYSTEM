package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.Review;
@Repository
public interface ReviewRepo extends JpaRepository<Review,Integer>{

}
