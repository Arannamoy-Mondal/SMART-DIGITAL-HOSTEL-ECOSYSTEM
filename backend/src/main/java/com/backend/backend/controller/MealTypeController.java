package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createMealType(
        @RequestBody MealTypeRequest mealTypeRequest
                ) {
            try {
           
                return ResponseEntity.status(HttpStatus.OK).body(mealTypeService.createMealType(mealTypeRequest));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }

    @GetMapping("/get")
    public ResponseEntity<?> getMealType(
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(mealTypeService.getAllMealType());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }

    @GetMapping("/get/{mealTypeId}")
    public ResponseEntity<?> getMealTypeById(
        @PathVariable("mealTypeId") Integer mealTypeId
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(mealTypeService.getMealTypeById(mealTypeId));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }
    
    @PutMapping("/update/{mealTypeId}")
    public ResponseEntity<?> updateMealType(
    @PathVariable("mealTypeId") Integer mealTypeId,
    @RequestBody MealTypeRequest mealTypeRequest            
    ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(mealTypeService.updateMealType(mealTypeId,mealTypeRequest));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }


    @DeleteMapping("/delete/{mealTypeId}")
    public ResponseEntity<?> deleteMealType(
        @PathVariable("mealTypeId") Integer mealTypeId
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(mealTypeService.deleteMealType(mealTypeId));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }

}
