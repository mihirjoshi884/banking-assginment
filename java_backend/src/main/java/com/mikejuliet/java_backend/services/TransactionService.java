package com.mikejuliet.java_backend.services;

import com.mikejuliet.java_backend.entities.Transaction;

import java.util.List;

public interface TransactionService {

    public Transaction saveTransactions(Transaction transaction);
    public List<Transaction> saveAllTransaction(List<Transaction> transactions);
    public List<Transaction> getAllTransactions(String userId, String bankId, String accountStatementId);
}
