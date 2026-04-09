package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.MenuItem;
@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem,Integer>{
    
}
