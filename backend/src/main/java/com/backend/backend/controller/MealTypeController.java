package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.MealTypeRequest;
import com.backend.backend.service.MealTypeService;


@RestController
@RequestMapping("/mealType")
public class MealTypeController {

    @Autowired
    private MealTypeService mealTypeService;


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createMealType(
        @RequestBody MealTypeRequest mealTypeRequest
                ) {
            try {
           
                return ResponseEntity.status(HttpStatus.OK).body(mealTypeService.createMealType(mealTypeRequest));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }

    @GetMapping("/get/all")
    public ResponseEntity<?> getMealType(
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(mealTypeService.getAllMealType());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }

    @GetMapping("/get/mealType/{mealType}")
    public ResponseEntity<?> getMealTypeByMealType(
        @PathVariable("mealType") String mealType
                ) {
            try {
               
                return ResponseEntity.status(HttpStatus.OK).body(mealTypeService.getMealTypeByMealType(mealType));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }
    
    // @PutMapping("/update/mealType/{mealType}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateMealType(
    @PathVariable("mealType") String mealType,
    @RequestBody MealTypeRequest mealTypeRequest            
    ) {
            try {
                System.out.println(mealType);
                return ResponseEntity.status(HttpStatus.OK).body(mealTypeService.updateMealType(mealType,mealTypeRequest));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }


    @DeleteMapping("/delete/mealType/{mealType}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteMealType(
        @PathVariable("mealType") String mealType
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(mealTypeService.deleteMealType(mealType));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }

}
