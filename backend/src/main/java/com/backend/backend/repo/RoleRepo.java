package com.backend.backend.repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    Optional<Role> findByRole(String role);
}
