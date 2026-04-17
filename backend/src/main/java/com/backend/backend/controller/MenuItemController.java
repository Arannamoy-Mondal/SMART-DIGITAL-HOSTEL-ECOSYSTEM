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

import com.backend.backend.dto.MenuItemRequest;
import com.backend.backend.service.MenuItemService;

@RestController
@RequestMapping("/menuItem")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/create")
    public ResponseEntity<?> createMenuItem(
        @RequestBody MenuItemRequest menuItemRequest
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(menuItemService.createMenuItem(menuItemRequest));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }

    @GetMapping("/get")
    public ResponseEntity<?> getMenuItem(
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(menuItemService.getMenuItem());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }

    @PutMapping("/update/{menuItemId}")
    public ResponseEntity<?> updateMenuItem(
        @PathVariable("menuItemId") Integer menuItemId,
        @RequestBody MenuItemRequest menuItemRequest
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(menuItemService.updateMenuItem(menuItemId,menuItemRequest));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }

    @DeleteMapping("/delete/{menuItemId}")
    public ResponseEntity<?> deleteMenuItem(
        @PathVariable("menuItemId") Integer menuItemId
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(menuItemService.deleteMenuItem(menuItemId));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }

    @GetMapping("/get/{menuItemId}")
    public ResponseEntity<?> getMenuItemById(
        @PathVariable("menuItemId") Integer menuItemId
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(menuItemService.getMenuItemById(menuItemId));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }
}
