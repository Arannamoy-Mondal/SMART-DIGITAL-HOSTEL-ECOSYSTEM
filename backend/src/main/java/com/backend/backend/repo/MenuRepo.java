package com.backend.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.Day;
import com.backend.backend.model.MealType;
import com.backend.backend.model.Menu;
@Repository
public interface MenuRepo extends JpaRepository<Menu,Integer>{
    Optional<Menu> findByDayAndMealType(Day day, MealType mealType);
}
