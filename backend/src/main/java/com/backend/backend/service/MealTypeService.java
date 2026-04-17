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
            MealType mealType = MealType.builder()
                    .mealType(mealTypeRequest.getMealType()).build();
            MealType savedMealType = mealTypeRepo.save(mealType);
            return savedMealType;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public @Nullable Object getAllMealType() throws Exception {
        try {
            List<MealType> mealTypes=mealTypeRepo.findAll();
            if(mealTypes.size()<1){
                throw new Exception("No MealType available");
            }
            return mealTypes;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object deleteMealType(Integer mealTypeId) throws Exception {
        try {
           
            MealType mealType=mealTypeRepo.findById(mealTypeId).orElse(null);
            if(mealType==null){
                throw new Exception("No meal type found with id "+mealTypeId);
            }
            mealTypeRepo.deleteById(mealTypeId);
            return "Successfully deleted";
            
        } catch (Exception e) {
            
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object updateMealType(Integer mealTypeId, MealTypeRequest mealTypeRequest) throws Exception {
        try {
            MealType mealType=mealTypeRepo.findById(mealTypeId).orElse(null);
            if(mealType==null){
                throw new Exception("No meal type found with id "+mealTypeId);
            }
            mealType.setMealType(mealTypeRequest.getMealType());
            MealType savedMealType=mealTypeRepo.save(mealType);
            return savedMealType;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
