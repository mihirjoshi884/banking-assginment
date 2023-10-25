package com.mikejuliet.java_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "tansactions")
public class Transaction {
    @Id
    String transactionId;
    String description;
    Date  dateOfTransaction;
    Integer credit;
    Integer debit;
    Integer balance;
    @ManyToOne
    @JoinColumn(name = "statement_id")
    private AccountStatement accountStatement;
}
