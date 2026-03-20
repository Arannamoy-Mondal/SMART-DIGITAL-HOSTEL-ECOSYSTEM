package com.backend.backend.service;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.backend.model.Role;
import com.backend.backend.repo.RoleRepo;

import tools.jackson.databind.ObjectMapper;

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

    public Role findByRole(String role){
        return roleRepo.findByRole(role);
    }
    public ResponseEntity<?> save(String body) {
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            Role role1=objectMapper.readValue(body, Role.class);
            Role role2 = Role.builder().role(role1.getRole()).build();
            roleRepo.save(role2);
            return ResponseEntity.status(HttpStatus.OK).body(roleRepo.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
    }
}
