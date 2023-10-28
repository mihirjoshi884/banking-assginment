package com.mikejuliet.java_backend.services.service_impl;

import com.mikejuliet.java_backend.entities.AccountStatement;
import com.mikejuliet.java_backend.entities.BankDetails;
import com.mikejuliet.java_backend.entities.Transaction;
import com.mikejuliet.java_backend.entities.UserDetails;
import com.mikejuliet.java_backend.repositories.TransactionRepository;
import com.mikejuliet.java_backend.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserDetailServiceImpl userService;
    @Override
    public Transaction saveTransactions(Transaction transaction) {
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> saveAllTransaction(List<Transaction> transactions) {
        return transactionRepository.saveAll(transactions);

    }

    @Override
    public List<Transaction> getAllTransactions(String userId, String bankId, String accountStatementId) {
        // Fetch the user by userId
        UserDetails user = userService.getUserDetailsByUsername(userId);

        if (user != null) {
            // Find the bank by bankId in the user's bank details
            Optional<BankDetails> bank = user.getBankDetails().stream()
                    .filter(b -> b.getBankId().equals(bankId))
                    .findFirst();

            if (bank.isPresent()) {
                // Find the account statement by accountStatementId in the bank's account statements
                Optional<AccountStatement> accountStatement = bank.get().getAccountStatements().stream()
                        .filter(a -> a.getStatementId().equals(accountStatementId))
                        .findFirst();

                if (accountStatement.isPresent()) {
                    // Return the transactions associated with the account statement
                    return accountStatement.get().getTransactions();
                }
            }
        }

        return Collections.emptyList(); // Return an empty list if the user, bank, or account statement is not found
    }

}
