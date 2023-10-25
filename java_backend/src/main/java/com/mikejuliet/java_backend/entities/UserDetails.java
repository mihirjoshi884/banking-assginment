package com.mikejuliet.java_backend.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "user_details")
public class UserDetails {
    @Id
    String userId;
    String name;
    String phone;
    String email;
    @Column(name = "userName", unique = true)
    String userName;
    String password;
    String address;
    @OneToMany(mappedBy = "userDetails")
    private List<BankDetails> bankDetails;

}
