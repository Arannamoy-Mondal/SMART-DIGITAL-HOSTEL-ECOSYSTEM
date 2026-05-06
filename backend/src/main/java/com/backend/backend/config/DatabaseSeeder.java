package com.backend.backend.config;

import com.backend.backend.repo.MealItemRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;

import com.backend.backend.dto.UserRequest;
import com.backend.backend.model.MealType;
import com.backend.backend.model.MenuItem;
import com.backend.backend.model.PaymentMethod;
import com.backend.backend.model.PaymentPurpose;
import com.backend.backend.model.Role;
import com.backend.backend.model.TransactionType;
import com.backend.backend.repo.MealTypeRepo;
import com.backend.backend.repo.MenuItemRepo;
import com.backend.backend.repo.PaymentMethodRepo;
import com.backend.backend.repo.PaymentPurposeRepo;
import com.backend.backend.repo.RoleRepo;
import com.backend.backend.repo.TransactionTypeRepo;
import com.backend.backend.repo.UserRepo;
import com.backend.backend.service.UserService;

@Configuration
public class DatabaseSeeder implements CommandLineRunner{


    @Autowired
    private RoleRepo roleRepo;
    
    @Autowired
    private TransactionTypeRepo transactionTypeRepo;
    
    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    @Autowired
    private PaymentPurposeRepo paymentPurposeRepo;


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MealTypeRepo mealTypeRepo;

    @Autowired
    private MenuItemRepo menuItemRepo;

    @Autowired
    private UserService userService;


    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        if(roleRepo.count()==0){
            roleRepo.save(Role.builder().role("admin").build());
            roleRepo.save(Role.builder().role("tenant").build());
            roleRepo.save(Role.builder().role("warden").build());
        }

        if(transactionTypeRepo.count()==0){
            transactionTypeRepo.save(TransactionType.builder().transactionType("debit").build());
            transactionTypeRepo.save(TransactionType.builder().transactionType("credit").build());
        }
        
        if(paymentMethodRepo.count()==0){
            paymentMethodRepo.save(PaymentMethod.builder().paymentMethod("bkash").build());
            paymentMethodRepo.save(PaymentMethod.builder().paymentMethod("rocket").build());
            paymentMethodRepo.save(PaymentMethod.builder().paymentMethod("nagad").build());
            paymentMethodRepo.save(PaymentMethod.builder().paymentMethod("visa").build());
            paymentMethodRepo.save(PaymentMethod.builder().paymentMethod("master card").build());
        }

        if (paymentPurposeRepo.count()==0) {
            paymentPurposeRepo.save(PaymentPurpose.builder().paymentPurpose("room rent").build());
            paymentPurposeRepo.save(PaymentPurpose.builder().paymentPurpose("meal token").build());
            paymentPurposeRepo.save(PaymentPurpose.builder().paymentPurpose("room rent and meal token").build());
            paymentPurposeRepo.save(PaymentPurpose.builder().paymentPurpose("service charge").build());
        }


        if(mealTypeRepo.count()==0){
            mealTypeRepo.saveAll(
                List.of(
                    MealType.builder().mealType("breakfast").build(),
                    MealType.builder().mealType("lunch").build(),
                    MealType.builder().mealType("dinner").build()
                )
            );
        }

        if(menuItemRepo.count()==0){
            menuItemRepo.saveAll(
                List.of(
                    MenuItem.builder().itemName("egg curry").build(),
                    MenuItem.builder().itemName("rice").build(),
                    MenuItem.builder().itemName("lentils").build(),
                    MenuItem.builder().itemName("chicken curry").build(),
                    MenuItem.builder().itemName("fish curry").build()

                )
            );
        }

        if(userRepo.count()==0){
            UserRequest userRequest=new UserRequest();
            userRequest.setUserName("admin");
            userRequest.setRole("admin");
            userRequest.setPassword("1234");
            userService.signup(userRequest);
        }
    }

}
