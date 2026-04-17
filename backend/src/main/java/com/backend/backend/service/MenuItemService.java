package com.backend.backend.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.MenuItemRequest;
import com.backend.backend.model.MenuItem;
import com.backend.backend.repo.MenuItemRepo;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepo menuItemRepo;

    public @Nullable Object createMenuItem(MenuItemRequest menuItemRequest) throws Exception {
        try {
            MenuItem tmp = menuItemRepo.findByItemName(menuItemRequest.itemName().toLowerCase()).orElse(null);
            if (tmp!=null) {
                throw new Exception("Menu item already exist");
            }
            MenuItem menuItem = MenuItem.builder()
                    .itemName(menuItemRequest.itemName().toLowerCase())
                    .description(menuItemRequest.description())
                    .build();
            MenuItem savedMenuItem = menuItemRepo.save(menuItem);
            return savedMenuItem;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object getMenuItem() throws Exception {
        try {
            List<MenuItem> menuItems = menuItemRepo.findAll();
            if (menuItems.size() == 0) {
                throw new Exception("No menu item found");
            }
            return menuItems;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object updateMenuItem(Integer menuItemId, MenuItemRequest menuItemRequest) throws Exception {
        try {
            MenuItem menuItem = menuItemRepo.findById(menuItemId).orElse(null);
            if (menuItem == null) {
                throw new Exception("No menu item found with id " + menuItemId);
            }
            if (menuItemRequest.itemName() != null && !menuItemRequest.itemName().isBlank()) {
                menuItem.setItemName(menuItemRequest.itemName().toLowerCase());
            }
            if(menuItemRequest.description()!=null && !menuItemRequest.description().isBlank()){
                menuItem.setDescription(menuItemRequest.description());
            }
            MenuItem savedMenuItem=menuItemRepo.save(menuItem);
            return savedMenuItem;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object deleteMenuItem(Integer menuItemId) throws Exception{
        try {
           MenuItem menuItem = menuItemRepo.findById(menuItemId).orElse(null);
            if (menuItem == null) {
                throw new Exception("No menu item found with id " + menuItemId);
            }
            menuItemRepo.delete(menuItem);
            return "Successfully deleted";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object getMenuItemById(Integer menuItemId) throws Exception{
        try {
           MenuItem menuItem = menuItemRepo.findById(menuItemId).orElse(null);
            if (menuItem == null) {
                throw new Exception("No menu item found with id " + menuItemId);
            }
            return menuItem;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
