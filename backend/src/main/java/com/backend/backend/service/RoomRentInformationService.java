package com.backend.backend.service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.RoomRentInformationRequest;
import com.backend.backend.model.Room;
import com.backend.backend.model.RoomRentInformation;
import com.backend.backend.model.User;
import com.backend.backend.repo.RoomRentInformationRepo;
import com.backend.backend.repo.RoomRepo;
import com.backend.backend.repo.UserRepo;

@Service
public class RoomRentInformationService {
   @Autowired
   public RoomRentInformationRepo rentInformationRepo;

   @Autowired
   public UserRepo userRepo;

   @Autowired
   public RoomRepo roomRepo;

   public @Nullable Object getRoomRentInformation() throws Exception {
      try {
         return rentInformationRepo.findAll();
      } catch (Exception e) {
         // TODO: handle exception
         throw new Exception(e.getMessage());
      }
   }

   public @Nullable Object getRoomInformationByUserName(String userName) throws Exception {
      try {
         User user = userRepo.findByUserName(userName).orElseThrow(
               () -> new Exception("No user found with username: " + userName));
         return rentInformationRepo.findByUser(user);

      } catch (Exception e) {
         // TODO: handle exception
         throw new Exception(e.getMessage());
      }
   }

   public @Nullable Object createRoomRentInformation(RoomRentInformationRequest roomRentInformationRequest)
         throws Exception {
      try {


         User user=userRepo.findByUserName(roomRentInformationRequest.userName())
         .orElseThrow(()->new Exception("No user found with username: "+roomRentInformationRequest.userName()));

         Room room=roomRepo.findByRoomNo(roomRentInformationRequest.roomNo())
         .orElseThrow(()->new Exception("No room found with room no: "+roomRentInformationRequest.roomNo()));


         RoomRentInformation res = RoomRentInformation.builder()
               .roomRentDays(roomRentInformationRequest.roomRentDays())
               .startDate(roomRentInformationRequest.startDate())
               .endDate(roomRentInformationRequest.endDate())
               // .mealStatus(roomRentInformationRequest.mealStatus())
               .user(user)
               .room(room)
               .build();
               return rentInformationRepo.save(res);
      } catch (Exception e) {
         // TODO: handle exception
         throw new Exception(e.getMessage());
      }
   }

}
