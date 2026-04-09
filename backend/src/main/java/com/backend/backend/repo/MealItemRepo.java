package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.model.MealItem;

public interface MealItemRepo extends JpaRepository<Integer,MealItem>{

}
