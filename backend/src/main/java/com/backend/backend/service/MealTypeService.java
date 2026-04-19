package com.backend.backend.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.MealTypeRequest;
import com.backend.backend.model.MealType;
import com.backend.backend.repo.MealTypeRepo;

@Service
public class MealTypeService {

    @Autowired
    private MealTypeRepo mealTypeRepo;

    public @Nullable Object createMealType(MealTypeRequest mealTypeRequest) throws Exception {
        try {
            MealType tmp=mealTypeRepo.findByMealType(mealTypeRequest.getMealType().toLowerCase()).orElse(null);
            if(tmp!=null){
                throw new Exception("Meal type already exist");
            }
            MealType mealType = MealType.builder()
                    .mealType(mealTypeRequest.getMealType().toLowerCase()).build();
            MealType savedMealType = mealTypeRepo.save(mealType);
            return savedMealType;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public @Nullable Object getAllMealType() throws Exception {
        try {
            List<MealType> mealTypes = mealTypeRepo.findAll();
            if (mealTypes.size() < 1) {
                throw new Exception("No MealType available");
            }
            return mealTypes;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object deleteMealType(String type) throws Exception {
        try {

            MealType mealType = mealTypeRepo.findByMealType(type).orElse(null);
            if (mealType == null) {
                throw new Exception("No meal type found with id " + type);
            }
            mealTypeRepo.deleteById(mealType.getMealTypeId());
            return "Successfully deleted";

        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object updateMealType(String type, MealTypeRequest mealTypeRequest) throws Exception {
        try {
            MealType mealType = mealTypeRepo.findByMealType(type).orElse(null);
            if (mealType == null) {
                throw new Exception("No meal type found with type " + type);
            }
            if (mealTypeRequest.getMealType() != null && !mealTypeRequest.getMealType().isBlank()) {
                mealType.setMealType(mealTypeRequest.getMealType().toLowerCase());

            }
            MealType savedMealType = mealTypeRepo.save(mealType);
            return savedMealType;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object getMealTypeByMealType(String type) throws Exception {
        try {

            MealType mealType = mealTypeRepo.findByMealType(type).orElse(null);
            if (mealType == null) {
                throw new Exception("No meal type found with meal type: " + type);
            }

            return mealType;

        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }
    }

}
