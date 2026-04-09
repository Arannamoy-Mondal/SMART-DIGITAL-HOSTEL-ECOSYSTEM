package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.UserMealHistory;
@Repository
public interface UserMealHistoryRepo extends JpaRepository<UserMealHistory,Integer>{

}
