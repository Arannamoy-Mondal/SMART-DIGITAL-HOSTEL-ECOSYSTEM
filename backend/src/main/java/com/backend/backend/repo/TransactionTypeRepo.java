package com.backend.backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.TransactionType;
@Repository
public interface TransactionTypeRepo extends JpaRepository<TransactionType,Integer>{
        Optional<TransactionType> findByTransactionType(String transactionType);
}
