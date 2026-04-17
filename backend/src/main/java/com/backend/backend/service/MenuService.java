package com.backend.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.MenuRequest;
import com.backend.backend.model.Day;
import com.backend.backend.model.MealType;
import com.backend.backend.model.Menu;
import com.backend.backend.model.MenuItem;
import com.backend.backend.repo.MealTypeRepo;
import com.backend.backend.repo.MenuItemRepo;
import com.backend.backend.repo.MenuRepo;

@Service
public class MenuService {

    @Autowired
    private MenuRepo menuRepo;
    @Autowired
    private MenuItemRepo menuItemRepo;
    @Autowired
    private MealTypeRepo mealTypeRepo;

    public @Nullable Object createMenu(MenuRequest menuRequest) throws Exception {
        try {
            MealType mealType = mealTypeRepo.findByMealType(menuRequest.mealType().toLowerCase())
                    .orElseThrow(() -> new Exception("Meal type "+menuRequest.mealType()+" not found: " + menuRequest.mealType()));

            if (menuRepo.findByDayAndMealType(Day.valueOf(menuRequest.day().toUpperCase()), mealType).isPresent()) {
                throw new Exception(Day.valueOf(menuRequest.day().toUpperCase()) + " " + menuRequest.mealType()
                        + " menu already exists!");
            }
            List<MenuItem> menuItems = new ArrayList<>();
            String message = "";
            for (String item : menuRequest.menuItems()) {
                MenuItem tmp = menuItemRepo.findByItemName(item.toString().toLowerCase()).orElse(null);
                if (tmp == null) {
                    message += item;
                    message += " not available.\n";
                } else {
                    menuItems.add(tmp);
                }
            }
            Menu menu = Menu.builder()
                    .menuName(menuRequest.day().toLowerCase()+" "+menuRequest.mealType().toLowerCase())
                    .day(Day.valueOf(menuRequest.day().toUpperCase()))
                    .mealType(mealType)
                    .menuItems(menuItems)
                    .build();
            Menu savedMenu = menuRepo.save(menu);
            return savedMenu;
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object getMenu() throws Exception {
        try {
            List<Menu> menus = menuRepo.findAll();
            if (menus.size() < 1) {
                throw new Exception("No menu item found");
            }
            return menus;
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object getMenuById(Integer menuId) throws Exception {
        try {
            Menu menu = menuRepo.findById(menuId).orElse(null);
            if (menu == null) {
                throw new Exception("No menu found");
            }
            return menu;
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object updateMenu(Integer menuId, MenuRequest menuRequest) throws Exception {
        try {
            Menu existingMenu = menuRepo.findById(menuId)
                    .orElseThrow(() -> new Exception("No menu found with ID: " + menuId));

            Day newDay = existingMenu.getDay();
            MealType newMealType = existingMenu.getMealType();
            boolean isDayOrMealTypeChanged = false;

            if (menuRequest.day() != null && !menuRequest.day().isBlank()) {
                newDay = Day.valueOf(menuRequest.day().toUpperCase());
                if (!newDay.equals(existingMenu.getDay())) {
                    isDayOrMealTypeChanged = true;
                }
            }

            if (menuRequest.mealType() != null && !menuRequest.mealType().isBlank()) {
                newMealType = mealTypeRepo.findByMealType(menuRequest.mealType().toLowerCase())
                        .orElseThrow(() -> new Exception("Meal type not found: " + menuRequest.mealType()));
                
                if (!newMealType.getMealTypeId().equals(existingMenu.getMealType().getMealTypeId())) {
                    isDayOrMealTypeChanged = true;
                }
            }

            if (isDayOrMealTypeChanged) {
                Menu conflictingMenu = menuRepo.findByDayAndMealType(newDay, newMealType).orElse(null);
                
                if (conflictingMenu != null && !conflictingMenu.getMenuId().equals(menuId)) {
                    throw new Exception(newDay + " " + newMealType.getMealType() + " menu already exists!");
                }
                
            
                existingMenu.setDay(newDay);
                existingMenu.setMealType(newMealType);
                
        
                existingMenu.setMenuName(newDay.name().toLowerCase() + " " + newMealType.getMealType().toLowerCase());
            }

           
            if (menuRequest.menuItems() != null && !menuRequest.menuItems().isEmpty()) {
                List<MenuItem> updatedMenuItems = new ArrayList<>();
                for (String item : menuRequest.menuItems()) {
                    MenuItem tmp = menuItemRepo.findByItemName(item.toLowerCase()).orElse(null);
                    if (tmp != null) {
                        updatedMenuItems.add(tmp);
                    }
                }

                existingMenu.setMenuItems(updatedMenuItems);
            }

            return menuRepo.save(existingMenu);

        } catch (IllegalArgumentException e) {
            throw new Exception("Invalid Day provided. Please use correct day names.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object deleteMenu(Integer menuId) throws Exception {
        try {
            Menu menu = menuRepo.findById(menuId).orElse(null);
            if (menu == null) {
                throw new Exception("No menu found");
            }
            menuRepo.delete(menu);
            return "Successfully deleted";
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.getMessage());
        }
    }

}
