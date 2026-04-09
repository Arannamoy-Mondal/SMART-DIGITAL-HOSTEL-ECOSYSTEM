package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.MealTokenHistory;
@Repository
public interface MealTokenHistoryRepo extends JpaRepository<MealTokenHistory,Integer>{

}
