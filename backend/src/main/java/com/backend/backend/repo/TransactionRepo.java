package com.backend.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.Room;
import com.backend.backend.model.Transaction;
import com.backend.backend.model.User;
@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer>{
    List<Transaction> findByUser(User user);
    List<Transaction> findByRoom(Room room);
}
