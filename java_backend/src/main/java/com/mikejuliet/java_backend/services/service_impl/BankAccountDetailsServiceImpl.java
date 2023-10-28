package com.mikejuliet.java_backend.services.service_impl;

import com.mikejuliet.java_backend.entities.BankDetails;
import com.mikejuliet.java_backend.entities.UserDetails;
import com.mikejuliet.java_backend.repositories.BankDetailsRepository;
import com.mikejuliet.java_backend.services.BankAccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BankAccountDetailsServiceImpl implements BankAccountDetailsService {
    public static List<BankDetails> bankAccounts = new ArrayList<>();

    @Autowired
    UserDetailServiceImpl userService;
    @Autowired
    BankDetailsRepository bankRepo;
    @Override
    public BankDetails saveBankAccountDetails(String userName,BankDetails bankDetails) {
        UserDetails userDetails = userService.getUserDetailsByUsername(userName);
        String bankId = UUID.randomUUID().toString();
        bankDetails.setBankId(bankId);
        bankDetails.setUserDetails(userDetails);
        bankRepo.save(bankDetails);
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
        // Assuming you have a UserDetail entity with a user ID and a list of associated bank details
        UserDetails user = userService.getUserDetailsByUsername(userName);
        if (user != null) {
            return user.getBankDetails(); // Assuming you have a getter for bankDetails in UserDetails
        }
        return Collections.emptyList(); // Return an empty list if the user is not found
    }


    @Override
    public BankDetails editBankDetails(String username, BankDetails bankDetails) {
        bankAccounts = userService.getUserDetailsByUsername(username).getBankDetails();
        Optional<BankDetails> matchingBankDetail = bankAccounts.stream()
                .filter(account -> account.getAccountNumber().equals(bankDetails.getAccountNumber()))
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
