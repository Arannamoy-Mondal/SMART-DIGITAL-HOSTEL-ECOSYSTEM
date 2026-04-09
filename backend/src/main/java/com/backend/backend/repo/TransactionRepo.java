package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.Transaction;
@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer>{

}
