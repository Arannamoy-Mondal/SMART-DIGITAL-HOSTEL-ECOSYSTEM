package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.model.TransactionType;

public interface TransactionTypeRepo extends JpaRepository<TransactionType,Integer>{

}
