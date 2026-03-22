package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction,Integer>{

}
