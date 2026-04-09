package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.model.MenuItem;

public interface MenuItemRepo extends JpaRepository<Integer,MenuItem>{
    
}
