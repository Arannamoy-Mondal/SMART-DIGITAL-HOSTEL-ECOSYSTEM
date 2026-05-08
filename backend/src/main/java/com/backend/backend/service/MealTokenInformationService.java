package com.backend.backend.service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.backend.dto.MealTokenInformationRequest;
import com.backend.backend.model.MealTokenInformation;
import com.backend.backend.model.PaymentMethod;
import com.backend.backend.model.PaymentPurpose;
import com.backend.backend.model.Room;
import com.backend.backend.model.Transaction;
import com.backend.backend.model.TransactionType;
import com.backend.backend.model.User;
import com.backend.backend.repo.MealTokenInformationRepo;
import com.backend.backend.repo.PaymentMethodRepo;
import com.backend.backend.repo.PaymentPurposeRepo;
import com.backend.backend.repo.RoomRepo;
import com.backend.backend.repo.TransactionRepo;
import com.backend.backend.repo.TransactionTypeRepo;
import com.backend.backend.repo.UserRepo;

@Service
public class MealTokenInformationService {

    @Autowired
    private MealTokenInformationRepo mealTokenInformationRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    @Autowired
    private PaymentPurposeRepo paymentPurposeRepo;

    @Autowired
    private TransactionTypeRepo transactionTypeRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Transactional
    public @Nullable Object createmealTokenInformation(MealTokenInformationRequest request) throws Exception {
        try {
            User user = userRepo.findByUserName(request.userName())
                    .orElseThrow(() -> new Exception("No user found with username: " + request.userName()));

            // if (user.getMealTokenAmount() != null) {
            //     throw new Exception("Already have token. Available token amount: " + user.getMealTokenAmount());
            // }
            Room room = roomRepo.findByRoomNo(request.roomNo())
                    .orElseThrow(() -> new Exception("No room found with roomNo: " + request.roomNo()));
            PaymentMethod paymentMethod = paymentMethodRepo.findByPaymentMethod(request.paymentMethod())
                    .orElseThrow(
                            () -> new Exception("No payment method found with method: " + request.paymentMethod()));

            PaymentPurpose paymentPurpose = paymentPurposeRepo.findByPaymentPurpose("meal token")
                    .orElseThrow(() -> new Exception("No payment purpose found with purpose: meal token"));

            TransactionType transactionType = transactionTypeRepo.findByTransactionType("debit")
                    .orElseThrow(() -> new Exception("No transaction type found with type: debit"));

            Transaction transaction = Transaction.builder()
                    .user(user)
                    .room(room)
                    .transactionType(transactionType)
                    .paymentMethod(paymentMethod)
                    .paymentPurpose(paymentPurpose)
                    .amount(request.amount())
                    .build();
            transactionRepo.save(transaction);
            
            MealTokenInformation mealTokenInformation = MealTokenInformation
                    .builder()
                    .transaction(transaction)
                    .user(user)
                    .room(room)
                    .tokenAmount(request.tokenAmount())
                    .availableToken(request.tokenAmount())
                    .build();
            user.setMealTokenAmount(request.tokenAmount());
            userRepo.save(user);
            return mealTokenInformationRepo.save(mealTokenInformation);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object getMealTokenInformationByUser(String userName) throws Exception {
        try {
            User user = userRepo.findByUserName(userName)
                    .orElseThrow(() -> new Exception("No user found with username: " + userName));
            return mealTokenInformationRepo.findByUser(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object getAllMealTokenInformation() throws Exception {
        try {
            return mealTokenInformationRepo.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
