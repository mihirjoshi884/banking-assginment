package com.mikejuliet.java_backend.services.service_impl;

import com.mikejuliet.java_backend.entities.AccountStatement;
import com.mikejuliet.java_backend.entities.BankDetails;
import com.mikejuliet.java_backend.entities.UserDetails;
import com.mikejuliet.java_backend.repositories.AccountStatementRepository;
import com.mikejuliet.java_backend.services.AccountStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccountStatementsServiceImpl implements AccountStatementService {
    @Autowired
    AccountStatementRepository acountRepo;
    @Autowired
    BankAccountDetailsServiceImpl bankService;
    @Autowired
    UserDetailServiceImpl userService;
    @Override
    public AccountStatement saveAccountStatement(AccountStatement statement) {
        acountRepo.save(statement);
        return statement;
    }

    @Override
    public List<AccountStatement> saveAllAccountStatement(List<AccountStatement> statements) {
        acountRepo.saveAll(statements);
        return statements;
    }

    @Override
    public List<AccountStatement> getAllAccountStatements(String userId, String bankId) {
        // Fetch the user by userId
        UserDetails user = userService.getUserDetailsByUsername(userId);

        if (user != null) {
            // Find the bank by bankId in the user's bank details
            Optional<BankDetails> bank = user.getBankDetails().stream()
                    .filter(b -> b.getBankId().equals(bankId))
                    .findFirst();

            if (bank.isPresent()) {
                // Return the account statements associated with the bank
                return bank.get().getAccountStatements();
            }
        }

        return Collections.emptyList(); // Return an empty list if the user or bank is not found
    }

}
