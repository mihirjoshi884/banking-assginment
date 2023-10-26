package com.mikejuliet.java_backend.services.service_impl;

import com.mikejuliet.java_backend.entities.BankDetails;
import com.mikejuliet.java_backend.entities.UserDetails;
import com.mikejuliet.java_backend.services.BankAccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankAccountDetailsServiceImpl implements BankAccountDetailsService {
    public static List<BankDetails> bankAccounts = new ArrayList<>();

    @Autowired
    UserDetailServiceImpl userService;
    @Override
    public BankDetails saveBankAccountDetails(String userName,BankDetails bankDetails) {
        UserDetails userDetails = userService.getUserDetailsByUsername(userName);
        String bankId = UUID.randomUUID().toString();
        bankDetails.setBankId(bankId);
        if (userDetails.getBankDetails() == null){
            bankAccounts.add(bankDetails);
            userDetails.setBankDetails(bankAccounts);
        }
        else{
            bankAccounts = userDetails.getBankDetails();
            bankAccounts.add(bankDetails);
        }
        return bankDetails;
    }

    @Override
    public List<BankDetails> getAllBankDetails(String userName) {
        return userService.getUserDetailsByUsername(userName).getBankDetails();
    }

    @Override
    public BankDetails editBankDetails(String username, BankDetails bankDetails) {
        bankAccounts = userService.getUserDetailsByUsername(username).getBankDetails();
        Optional<BankDetails> matchingBankDetail = bankAccounts.stream()
                .filter(account -> account.getAC_number().equals(bankDetails.getAC_number()))
                .findFirst();
        if (matchingBankDetail.isPresent()) {
            // Modify the matching bank detail (if needed)
            BankDetails foundBankDetail = matchingBankDetail.get();
            foundBankDetail.setBankName(bankDetails.getBankName());
            foundBankDetail.setAccountType(bankDetails.getAccountType());
            foundBankDetail.setBranchName(bankDetails.getBranchName());
            return foundBankDetail;
        }
        else {return null;}
    }


}
