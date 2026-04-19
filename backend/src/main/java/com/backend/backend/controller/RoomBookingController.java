package com.backend.backend.controller;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.RoomBookingRequest;
import com.backend.backend.model.PaymentMethod;
import com.backend.backend.model.Room;
import com.backend.backend.model.RoomRentInformation;
import com.backend.backend.model.Transaction;
import com.backend.backend.model.User;
import com.backend.backend.repo.PaymentMethodRepo;
import com.backend.backend.repo.PaymentPurposeRepo;
import com.backend.backend.repo.RoomRentInformationRepo;
import com.backend.backend.repo.RoomRepo;
import com.backend.backend.repo.TransactionRepo;
import com.backend.backend.repo.TransactionTypeRepo;
import com.backend.backend.repo.UserRepo;

@RestController
@RequestMapping("/roomBooking")
public class RoomBookingController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private RoomRentInformationRepo roomRentInformationRepo;

    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    @Autowired
    private PaymentPurposeRepo paymentPurposeRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private TransactionTypeRepo transactionTypeRepo;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<?> createRoomBooking(
            @RequestBody RoomBookingRequest request) {
        try {

            User user = userRepo.findByUserName(request.userName())
                    .orElseThrow(() -> new Exception("no user found with user: " + request.userName()));

            Room room = roomRepo.findByRoomNo(request.roomNo())
                    .orElseThrow(() -> new Exception("no room found with room no: " + request.roomNo()));

            List<RoomRentInformation> userBookings = roomRentInformationRepo.findByUserAndIsActiveTrue(user);
            for (RoomRentInformation existingBooking : userBookings) {

                if (!request.startDate().isAfter(existingBooking.getEndDate()) &&
                        !request.endDate().isBefore(existingBooking.getStartDate())) {
                    throw new Exception("You already have a booking from " + existingBooking.getStartDate() +
                            " to " + existingBooking.getEndDate() + " which overlaps with these dates.");
                }
            }

            List<RoomRentInformation> roomBookings = roomRentInformationRepo.findByRoomAndIsActiveTrue(room);
            int overlappingRoomBookings = 0;

            for (RoomRentInformation existingRoomBooking : roomBookings) {

                if (!request.startDate().isAfter(existingRoomBooking.getEndDate()) &&
                        !request.endDate().isBefore(existingRoomBooking.getStartDate())) {
                    overlappingRoomBookings++;
                }
            }
            if (overlappingRoomBookings >= room.getTotalSeat()) {
                throw new Exception("The room is already fully booked for your selected dates.");
            }
            
            if (room.isOccupied()) {
                throw new Exception("already occupied");
            }

            room.setAvailableSeat(room.getAvailableSeat() - 1);
            if (room.getAvailableSeat() == 0) {
                room.setOccupied(true);
            }

            roomRepo.save(room);

            PaymentMethod paymentMethod = paymentMethodRepo.findByPaymentMethod(request.paymentMethod())
                    .orElseThrow(
                            () -> new Exception("no payment method found with method : " + request.paymentMethod()));

            Transaction transaction = Transaction.builder()
                    .user(user)
                    .room(room)
                    .transactionType(transactionTypeRepo.findByTransactionType("debit").orElse(null))
                    .paymentMethod(paymentMethod)
                    .paymentPurpose(paymentPurposeRepo.findByPaymentPurpose("room rent").orElse(null))
                    .amount(request.amount())
                    .build();
            transactionRepo.save(transaction);
            RoomRentInformation roomRentInformation = RoomRentInformation.builder()
                    .user(user)
                    .room(room)
                    .startDate(request.startDate())
                    .endDate(request.endDate())
                    .roomRentDays((int) ChronoUnit.DAYS.between(request.startDate(), request.endDate()))
                    .transaction(transaction)
                    .isActive(true)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(roomRentInformationRepo.save(roomRentInformation));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAllRoomBooking(

    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(roomRentInformationRepo.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/get/userName/{userName}")
    public ResponseEntity<?> getRoomBookingByUserName(
            @PathVariable("userName") String userName) {
        try {

            User user = userRepo.findByUserName(userName).orElseThrow(
                    () -> new Exception("no user found with username: " + userName));
            return ResponseEntity.status(HttpStatus.OK).body(roomRentInformationRepo.findByUser(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
