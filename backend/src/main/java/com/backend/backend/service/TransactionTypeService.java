package com.backend.backend.service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.TransactionTypeRequest;
import com.backend.backend.model.TransactionType;
import com.backend.backend.repo.TransactionTypeRepo;

@Service
public class TransactionTypeService {

    @Autowired
    private TransactionTypeRepo transactionTypeRepo;

    public @Nullable Object createTransactionType(TransactionTypeRequest transactionTypeRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTransactionType'");
    }

    public @Nullable Object getTransactionType() throws Exception {
        try {
            return transactionTypeRepo.findAll();
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object getTransactionTypeByTransactionType(String transactionType) throws Exception{
       try {
         TransactionType tmp=transactionTypeRepo.findByTransactionType(transactionType.toLowerCase())
         .orElseThrow(()->new Exception("No transactype found with type: "+transactionType.toLowerCase()));
         return tmp;
       } catch (Exception e) {
        throw new Exception(e.getMessage());
       }
    }

}
