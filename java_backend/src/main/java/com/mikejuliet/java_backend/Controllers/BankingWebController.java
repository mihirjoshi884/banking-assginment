package com.mikejuliet.java_backend.Controllers;

import com.mikejuliet.java_backend.entities.AccountStatement;
import com.mikejuliet.java_backend.entities.BankDetails;
import com.mikejuliet.java_backend.entities.Transaction;
import com.mikejuliet.java_backend.entities.UserDetails;
import com.mikejuliet.java_backend.services.service_impl.AccountStatementsServiceImpl;
import com.mikejuliet.java_backend.services.service_impl.BankAccountDetailsServiceImpl;
import com.mikejuliet.java_backend.services.service_impl.TransactionServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class BankingWebController {
    @Autowired
    BankAccountDetailsServiceImpl bankService;
    @Autowired
    AccountStatementsServiceImpl accountStatementService;
    @Autowired
    TransactionServiceImpl transactionService;
    @RequestMapping("/banking-web/home")
    public String home(){
        return "home.jsp";
    }

    @RequestMapping("/banking-web/login")
    public String login(){
        return "login.jsp";
    }

    @RequestMapping("/banking-web/signUp")
    public String signUp(){
        return "signUp.jsp";
    }

    @RequestMapping("/banking-web/dashborad")
    public String dashboard(){ return "dashboard.jsp";}
    @GetMapping("/bank-details-list")
    public String listBankDetails(Model model, HttpServletRequest request) {
        UserDetails user = (UserDetails) request.getSession().getAttribute("user-details");
        // Retrieve the list of bank details from your service
        List<BankDetails> bankDetails = bankService.getAllBankDetails(user.getUserName());

        // Add the list of bank details to the model
        model.addAttribute("bankDetails", bankDetails);

        // Return the name of the JSP view that will display the list of bank details
        return "bank-details-list";
    }
    @GetMapping("/account-statements-list")
    public String listAccountStatements(Model model,HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) request.getSession().getAttribute("user-details");
        BankDetails bankDetails = (BankDetails) request.getSession().getAttribute("bank-details");

        // Retrieve the list of account statements from your service
        List<AccountStatement> accountStatements = accountStatementService.getAllAccountStatements(userDetails.getUserId(),bankDetails.getBankId());

        // Add the list of account statements to the model
        model.addAttribute("accountStatements", accountStatements);

        // Return the name of the JSP view that will display the list of account statements
        return "account-statements-list";
    }
    @GetMapping("/transactions-list")
    public String listTransactions(Model model,HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) request.getSession().getAttribute("user-details");
        BankDetails bankDetails = (BankDetails) request.getSession().getAttribute("bank-details");
        AccountStatement accountStatement = (AccountStatement) request.getSession().getAttribute("accountStatements");
        // Retrieve the list of transactions from your service
        List<Transaction> transactions = transactionService.getAllTransactions(userDetails.getUserId(),bankDetails.getBankId(),accountStatement.getStatementId());

        // Add the list of transactions to the model
        model.addAttribute("transactions", transactions);

        // Return the name of the JSP view that will display the list of transactions
        return "transactions-list";
    }


}
