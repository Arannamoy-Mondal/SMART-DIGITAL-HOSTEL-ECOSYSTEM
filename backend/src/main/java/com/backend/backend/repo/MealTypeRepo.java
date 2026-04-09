package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.MealType;
@Repository
public interface MealTypeRepo extends JpaRepository<MealType,Integer>{

}
