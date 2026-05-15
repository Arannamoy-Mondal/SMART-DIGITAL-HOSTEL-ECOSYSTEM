package com.backend.backend.service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.backend.dto.MealTokenConsumeRequest;
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




    @Transactional
    public @Nullable Object consumeMealToken(MealTokenConsumeRequest request) throws Exception {
        try {
            User user = userRepo.findByUserName(request.userName())
                    .orElseThrow(() -> new Exception("User not found: " + request.userName()));

            // 1. Validate token balance
            Integer currentTokens = user.getMealTokenAmount();
            if (currentTokens == null || currentTokens < request.deductAmount()) {
                throw new Exception("Insufficient tokens!");
            }

            // 2. Find necessary entities for Transaction
            PaymentPurpose purpose = paymentPurposeRepo.findAll().stream()
                    .filter(p -> p.getPaymentPurpose().equalsIgnoreCase("Meal"))
                    .findFirst().orElse(null);
            
            TransactionType type = transactionTypeRepo.findAll().stream()
                    .filter(t -> t.getTransactionType().equalsIgnoreCase("Debit"))
                    .findFirst().orElse(null);

            PaymentMethod method = paymentMethodRepo.findAll().stream()
                    .filter(m -> m.getPaymentMethod().equalsIgnoreCase("Token"))
                    .findFirst().orElse(null);

            // 3. Create and Save Transaction
            Transaction transaction = Transaction.builder()
                    .user(user)
                    .transactionType(type)
                    .paymentMethod(method)
                    .paymentPurpose(purpose)
                    // 💡 FIX: Integer থেকে BigDecimal এ কনভার্ট করা হয়েছে এবং অতিরিক্ত ')' সরানো হয়েছে
                    .amount(java.math.BigDecimal.valueOf(request.deductAmount())) 
                    .build();
            transactionRepo.save(transaction);

            // 4. Update User and MealTokenInformation
            user.setMealTokenAmount(currentTokens - request.deductAmount());
            userRepo.save(user);

            Object mealInfoObj = mealTokenInformationRepo.findByUser(user);
            if (mealInfoObj instanceof MealTokenInformation) {
                MealTokenInformation mealInfo = (MealTokenInformation) mealInfoObj;
                mealInfo.setAvailableToken(mealInfo.getAvailableToken() - request.deductAmount());
                mealTokenInformationRepo.save(mealInfo);
            } else if (mealInfoObj instanceof java.util.List) {
                java.util.List<MealTokenInformation> list = (java.util.List<MealTokenInformation>) mealInfoObj;
                if (!list.isEmpty()) {
                    MealTokenInformation mealInfo = list.get(list.size() - 1);
                    if (mealInfo.getAvailableToken() != null) {
                        mealInfo.setAvailableToken(mealInfo.getAvailableToken() - request.deductAmount());
                        mealTokenInformationRepo.save(mealInfo);
                    }
                }
            }

            return "Meal " + request.mealType() + " recorded successfully.";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
