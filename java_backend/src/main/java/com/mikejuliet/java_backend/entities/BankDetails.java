package com.mikejuliet.java_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "Bank_Details")
public class BankDetails {
    @Id
    String bankId;
    String bankName;
    String branchName;
    String accountType;
    @Column(name = "ac_number",unique = true)
    String accountNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetails userDetails;
    @OneToMany(mappedBy = "bankDetails")
    private List<AccountStatement> accountStatements;

}
