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

import com.backend.backend.dto.MenuRequest;
import com.backend.backend.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/create")
    public ResponseEntity<?> createMenu(
            @RequestBody MenuRequest menuRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menuService.createMenu(menuRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get")
    public ResponseEntity<?> getMenu() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menuService.getMenu());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get/{menuId}")
    public ResponseEntity<?> getMenuId(
            @PathVariable("menuId") Integer menuId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menuService.getMenuById(menuId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/update/{menuId}")
    public ResponseEntity<?> updateMenu(
            @PathVariable("menuId") Integer menuId,
            @RequestBody MenuRequest menuRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menuService.updateMenu(menuId, menuRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    @DeleteMapping("/delete/{menuId}")
    public ResponseEntity<?> deleteMenu(
        @PathVariable("menuId") Integer menuId
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(menuService.deleteMenu(menuId));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }
}
