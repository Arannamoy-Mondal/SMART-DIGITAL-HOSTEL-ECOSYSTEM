package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.RoleRequest;
import com.backend.backend.service.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/get")
    public ResponseEntity<?> findAll(){
       return roleService.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        return roleService.findById(id);
    }
    

    @PostMapping("/create")
    public ResponseEntity<?> createRole(
        @RequestBody RoleRequest roleRequest
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(roleService.create(roleRequest));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }

    @PutMapping("/update/{roleId}")
    public ResponseEntity<?> updateRole(
        @PathVariable("roleId") Integer roleId,
        @RequestBody RoleRequest roleRequest
                ) {
            try {
                System.out.println(roleRequest);
                return ResponseEntity.status(HttpStatus.OK).body(roleService.updateRole(roleId,roleRequest));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<?> deleteRole(
        @PathVariable("roleId") Integer roleId
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(roleService.deleteRole(roleId));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }
}
