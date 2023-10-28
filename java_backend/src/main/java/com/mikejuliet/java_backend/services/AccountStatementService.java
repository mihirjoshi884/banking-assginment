package com.mikejuliet.java_backend.services;

import com.mikejuliet.java_backend.entities.AccountStatement;

import java.util.List;

public interface AccountStatementService {

    public AccountStatement saveAccountStatement(AccountStatement statement);
    public List<AccountStatement> saveAllAccountStatement(List<AccountStatement> statements);
    public List<AccountStatement> getAllAccountStatements(String userId, String bankId);

}
