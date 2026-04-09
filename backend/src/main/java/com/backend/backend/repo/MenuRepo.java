package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.Menu;
@Repository
public interface MenuRepo extends JpaRepository<Menu,Integer>{

}
