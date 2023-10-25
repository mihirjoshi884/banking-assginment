package com.mikejuliet.java_backend.repositories;

import com.mikejuliet.java_backend.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails,String> {
    public UserDetails findUserDetailsByUserName(String userName);
}
