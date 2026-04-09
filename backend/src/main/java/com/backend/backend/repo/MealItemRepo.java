package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.MealItem;
@Repository
public interface MealItemRepo extends JpaRepository<MealItem,Integer>{

}
