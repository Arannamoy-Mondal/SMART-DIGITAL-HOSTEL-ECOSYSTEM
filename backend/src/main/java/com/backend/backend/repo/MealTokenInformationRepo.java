package com.backend.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.MealTokenInformation;
import com.backend.backend.model.User;

@Repository
public interface MealTokenInformationRepo extends JpaRepository<MealTokenInformation,Integer>{
    List<MealTokenInformation> findByUser(User user);
}
