package com.backend.backend.service;

import com.backend.backend.model.Room;
import com.backend.backend.model.RoomRentInformation;
import com.backend.backend.repo.RoomRentInformationRepo;
import com.backend.backend.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomCheckoutScheduler {

    @Autowired
    private RoomRentInformationRepo roomRentInformationRepo;

    @Autowired
    private RoomRepo roomRepo;

  
    @Scheduled(cron = "0 0 0 * * ?") 
    @Transactional
    public void autoReleaseExpiredRooms() {
        LocalDate today = LocalDate.now();
        
    
        List<RoomRentInformation> expiredBookings = roomRentInformationRepo.findByEndDateBeforeAndIsActiveTrue(today);


        for (RoomRentInformation booking : expiredBookings) {
            Room room = booking.getRoom();

     
            room.setAvailableSeat(room.getAvailableSeat() + 1);
            
    
            room.setOccupied(false); 
 
            roomRepo.save(room);

          
            booking.setActive(false);
            roomRentInformationRepo.save(booking);
            
            System.out.println("Auto checkout completed for User: " + booking.getUser().getUserName() + " Room No: " + room.getRoomNo());
        }
    }
}