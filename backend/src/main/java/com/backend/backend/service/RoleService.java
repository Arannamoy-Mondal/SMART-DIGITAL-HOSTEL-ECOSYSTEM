package com.backend.backend.service;

import java.util.ArrayList;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.RoleRequest;
import com.backend.backend.model.Role;
import com.backend.backend.repo.RoleRepo;



@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(roleRepo.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
    }

    public ResponseEntity<?> findById(Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(roleRepo.findById(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
    }

    public @Nullable Object create(RoleRequest roleRequest) throws Exception {
        try {
            Role role = roleRepo.findByRole(roleRequest.role().toLowerCase())
                    .orElse(null);
            if (role != null) {
                throw new Exception(roleRequest.role() + " role already exist.");
            }
            return roleRepo.save(Role.builder()
                    .role(roleRequest.role().toLowerCase()).build());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object updateRole(Integer roleId, RoleRequest roleRequest) throws Exception {
        try {
            Role role = roleRepo.findById(roleId).orElseThrow(
                    () -> new Exception("No role exist with id " + roleId));
            if (roleRequest.role() != null && !roleRequest.role().isBlank()) {
                role.setRole(roleRequest.role());
            }
            return roleRepo.save(role);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            throw new Exception(e.getMessage());
        }

    }

    public @Nullable Object deleteRole(Integer roleId) throws Exception{
        try {
            Role role = roleRepo.findById(roleId)
            .orElseThrow(() -> new Exception("No role exist with id " + roleId));
                roleRepo.deleteById(roleId);
                return "Successfully deleted";
        } catch (Exception e) {
              throw new Exception(e.getMessage());
        }
    }

}
