package com.mikejuliet.java_backend.repositories;

import com.mikejuliet.java_backend.entities.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails,String> {
}
