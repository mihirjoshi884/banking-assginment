package com.mikejuliet.java_backend.repositories;

import com.mikejuliet.java_backend.entities.AccountStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStatementRepository extends JpaRepository<AccountStatement,String> {
}
