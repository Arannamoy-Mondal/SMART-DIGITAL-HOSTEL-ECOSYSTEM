package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createMenuItem(
        @RequestBody MenuItemRequest menuItemRequest
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(menuItemRequest);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }
}
