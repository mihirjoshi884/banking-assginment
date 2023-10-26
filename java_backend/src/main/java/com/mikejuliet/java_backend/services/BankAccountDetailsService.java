package com.mikejuliet.java_backend.services;

import com.mikejuliet.java_backend.entities.BankDetails;

import java.util.List;

public interface BankAccountDetailsService {
    public BankDetails saveBankAccountDetails(String userName, BankDetails bankDetails);
    public List<BankDetails> getAllBankDetails(String userName);
    public BankDetails editBankDetails(String Username, BankDetails bankDetails);
}
