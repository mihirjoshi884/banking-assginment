package com.mikejuliet.java_backend.Controllers;

import com.mikejuliet.java_backend.entities.AccountStatement;
import com.mikejuliet.java_backend.entities.BankDetails;
import com.mikejuliet.java_backend.entities.Transaction;
import com.mikejuliet.java_backend.entities.UserDetails;
import com.mikejuliet.java_backend.entities.pojos.LoginRequest;
import com.mikejuliet.java_backend.payload.ApiResponse;
import com.mikejuliet.java_backend.services.PythonAPI_interfaceService;
import com.mikejuliet.java_backend.services.service_impl.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banking-backend/")
public class BackendController {

    @Autowired
    UserDetailServiceImpl userService;
    @Autowired
    UserOrchestrationServiceImpl orchestrationService;
    @Autowired
    BankAccountDetailsServiceImpl bankService;
    @Autowired
    TransactionServiceImpl transactionService;
    @Autowired
    AccountStatementsServiceImpl accountStatementService;
    @Autowired
    PythonAPI_interfaceService pythonAPI;

    public static ApiResponse response = new ApiResponse();

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody UserDetails userDetails) {
        try {
            UserDetails existingUser = userService.getUserDetailsByUsername(userDetails.getUserName());
            if (existingUser != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(existingUser);
            } else {
                UserDetails createdUser = userService.saveUsersDetails(userDetails);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
            }
        } catch (Exception e) {
            String errorMessage = "User creation failed. Please check the input data.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginIntoUserAccount(@RequestBody LoginRequest loginRequest, HttpSession session) {
        if (orchestrationService.userLogin(loginRequest.getUsername(), loginRequest.getPassword())) {
            session.setAttribute("user-details", userService.getUserDetailsByUsername(loginRequest.getUsername()));
            response.setMessage("login successful");
            response.setStatus(HttpStatus.OK);
            response.setSuccess(true);
            ApiResponse.builder().build();
            return ResponseEntity.status(201).body(response);
        } else {
            response.setSuccess(false);
            response.setMessage("unauthorized");
            response.setStatus(HttpStatus.UNAUTHORIZED);
            ApiResponse.builder().build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/add-back-ac-detail")
    public ResponseEntity<BankDetails> saveBankDetails(@RequestBody BankDetails bankDetails, HttpServletRequest request) {
        UserDetails user = (UserDetails) request.getSession().getAttribute("user-details");
        return ResponseEntity.status(201).body(bankService.saveBankAccountDetails(user.getUserName(), bankDetails));
    }

    @GetMapping("/get-userDetails/{username}")
    public ResponseEntity<UserDetails> getUserDetails(@PathVariable("username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserDetailsByUsername(username));
    }

    @GetMapping("/email-login/email/{email}/password/{password}/")
    public void emailLogin(@PathVariable("email") String email, @PathVariable("password") String password) {
        pythonAPI.emailLogin(email, password);
    }

    @GetMapping("/get-bank-details/userName/{userName}")
    public ResponseEntity<?> getBankDetails(@PathVariable("userName") String userName, HttpSession session) {
        session.setAttribute("bank-details", bankService.getAllBankDetails(userName));
        List<BankDetails> results = bankService.getAllBankDetails(userName);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @PostMapping("/log-out")
    public ResponseEntity<ApiResponse> logout(HttpSession session) {
        session.invalidate();
        response.setStatus(HttpStatus.OK);
        response.setMessage("session been invalidated");
        response.setSuccess(true);
        ApiResponse.builder().build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add-transaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction, HttpSession session) {
        Transaction savedTransaction = transactionService.saveTransactions(transaction);
        session.setAttribute("lastTransaction", savedTransaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }

    @PostMapping("/add-transactions")
    public ResponseEntity<List<Transaction>> addTransactions(@RequestBody List<Transaction> transactions, HttpSession session) {
        List<Transaction> savedTransactions = transactionService.saveAllTransaction(transactions);
        session.setAttribute("allTransactions", savedTransactions);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransactions);
    }

    @GetMapping("/get-transactions/user/{userId}/bank/{bankId}/account-statement/{accountStatementId}")
    public ResponseEntity<List<Transaction>> getAllTransactions(
            @PathVariable("userId") String userId,
            @PathVariable("bankId") String bankId,
            @PathVariable("accountStatementId") String accountStatementId,
            HttpSession session
    ) {
        List<Transaction> transactions = transactionService.getAllTransactions(userId, bankId, accountStatementId);
        session.setAttribute("allTransactions", transactions);
        return ResponseEntity.status(HttpStatus.OK).body(transactions);
    }

    @GetMapping("/get-account-statements/user/{userId}/bank/{bankId}")
    public ResponseEntity<List<AccountStatement>> getAllAccountStatements(
            @PathVariable("userId") String userId,
            @PathVariable("bankId") String bankId,
            HttpSession session
    ) {
        List<AccountStatement> accountStatements = accountStatementService.getAllAccountStatements(userId, bankId);
        session.setAttribute("allAccountStatements", accountStatements);
        return ResponseEntity.status(HttpStatus.OK).body(accountStatements);
    }
}
