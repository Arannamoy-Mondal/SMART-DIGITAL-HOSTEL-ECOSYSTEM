package com.backend.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.backend.backend.model.Role;
import com.backend.backend.model.TransactionType;
import com.backend.backend.repo.RoleRepo;
import com.backend.backend.repo.TransactionTypeRepo;

@Configuration
public class DatabaseSeeder implements CommandLineRunner{

    @Autowired
    private RoleRepo roleRepo;
    
    @Autowired
    private TransactionTypeRepo transactionTypeRepo;
    
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
        
    }

}
