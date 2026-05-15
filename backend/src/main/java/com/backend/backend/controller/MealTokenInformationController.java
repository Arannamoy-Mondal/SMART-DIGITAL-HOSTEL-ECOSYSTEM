package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.MealTokenConsumeRequest;
import com.backend.backend.dto.MealTokenInformationRequest;
import com.backend.backend.service.MealTokenInformationService;

@RestController
@RequestMapping("/mealToken")
public class MealTokenInformationController {

    @Autowired
    private MealTokenInformationService mealTokenInformationService;

    @PostMapping("/create")
    public ResponseEntity<?> createmealTokenInformation(
            @RequestBody MealTokenInformationRequest request) {
        try {
            System.out.println(request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(mealTokenInformationService.createmealTokenInformation(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get/userName/{userName}")
    public ResponseEntity<?> getMealTokenInformationByUser(
            @PathVariable("userName") String userName) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(mealTokenInformationService.getMealTokenInformationByUser(userName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAllMealTokenInformation() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mealTokenInformationService.getAllMealTokenInformation());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/consume")
    public ResponseEntity<?> consumeMealToken(@RequestBody MealTokenInformationRequest request) {
        try {
            System.out.println(request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(mealTokenInformationService.consumeMealToken(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
