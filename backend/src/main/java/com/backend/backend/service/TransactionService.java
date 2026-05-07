package com.backend.backend.service;

import java.math.BigDecimal;


import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.TransactionRequest;
import com.backend.backend.model.PaymentMethod;
import com.backend.backend.model.PaymentPurpose;
import com.backend.backend.model.Room;
import com.backend.backend.model.Transaction;
import com.backend.backend.model.TransactionType;
import com.backend.backend.model.User;
import com.backend.backend.repo.PaymentMethodRepo;
import com.backend.backend.repo.PaymentPurposeRepo;
import com.backend.backend.repo.RoomRepo;
import com.backend.backend.repo.TransactionRepo;
import com.backend.backend.repo.TransactionTypeRepo;
import com.backend.backend.repo.UserRepo;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private TransactionTypeRepo transactionTypeRepo;

    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    @Autowired
    private PaymentPurposeRepo paymentPurposeRepo;

    public @Nullable Object createTransaction(TransactionRequest transactionRequest) throws Exception{
        try {
            User user=userRepo.findByUserName(transactionRequest.userName()).orElseThrow(()->
            new Exception("No user found with "+transactionRequest.userName()));

            Room room=roomRepo.findByRoomNo(transactionRequest.roomNo()).orElseThrow(()->new Exception("No room found with "+transactionRequest.roomNo()));

            TransactionType transactionType=transactionTypeRepo.findByTransactionType(transactionRequest.transactionType()).orElseThrow(()->new Exception("No transaction type found with "+transactionRequest.transactionType()));

            PaymentMethod paymentMethod=paymentMethodRepo.findByPaymentMethod(transactionRequest.paymentMethod()).orElseThrow(()->new Exception("No payment method found with "+transactionRequest.paymentMethod()));

            PaymentPurpose paymentPurpose=paymentPurposeRepo.findByPaymentPurpose(transactionRequest.paymentPurpose()).orElseThrow(()->new Exception("No payment purpose found with "+transactionRequest.paymentPurpose()));

            BigDecimal amount=transactionRequest.amount();

            return transactionRepo.save(Transaction.builder()
            .user(user)
            .room(room)
            .transactionType(transactionType)
            .paymentMethod(paymentMethod)
            .paymentPurpose(paymentPurpose)
            .amount(amount)
            .build());

        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object getAllTransaction() throws Exception {
        try {
            return transactionRepo.findAll();
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object getTransactionByUserName(String username) throws Exception {
        try {
            User user=userRepo.findByUserName(username).orElseThrow(()->
            new Exception("No user found with "+username));

            return transactionRepo.findByUser(user);

        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object getTransactionByRoomNo(Integer roomNo) throws Exception {
        try {
            Room room=roomRepo.findByRoomNo(roomNo).orElseThrow(()->
            new Exception("No room no found with "+roomNo));

            return transactionRepo.findByRoom(room);

        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.getMessage());
        }
    }
}
