package com.mikejuliet.java_backend.repositories;

import com.mikejuliet.java_backend.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {
}
