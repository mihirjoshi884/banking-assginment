package com.mikejuliet.java_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "account_statement")
public class AccountStatement {
    @Id
    String statementId;
    Date statmentDate;
    Date startDate;
    Date endDate;
    Integer numberOfTransactions;
    Integer totalCreditAmount;
    Integer totalDebitAmount;
    Integer openningBalance;
    Integer closingBalance;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private BankDetails bankDetails;
    @OneToMany(mappedBy = "accountStatement")
    private List<Transaction> transactions;

}
