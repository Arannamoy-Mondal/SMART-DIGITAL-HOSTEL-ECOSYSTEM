package com.backend.backend.service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.model.User;
import com.backend.backend.repo.RoomRentInformationRepo;
import com.backend.backend.repo.UserRepo;

@Service
public class RoomRentInformationService {
    @Autowired
    public RoomRentInformationRepo rentInformationRepo;

    @Autowired
    public UserRepo userRepo;

    public @Nullable Object getRoomRentInformation() throws Exception{
       try {
         return rentInformationRepo.findAll();
       } catch (Exception e) {
        // TODO: handle exception
        throw new Exception(e.getMessage());
       }
    }

    public @Nullable Object getRoomInformationByUserName(String userName) throws Exception{
       try {
         User user=userRepo.findByUserName(userName).orElseThrow(
            ()->new Exception("No user found with username: "+userName)
         );
         return rentInformationRepo.findByUser(user);

       } catch (Exception e) {
        // TODO: handle exception
        throw new Exception(e.getMessage());
       }
    }



}
