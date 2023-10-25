package com.mikejuliet.java_backend.services;

import com.mikejuliet.java_backend.entities.UserDetails;

public interface UserDetailService {
    public UserDetails saveUsersDetails(UserDetails userDetails);
    public UserDetails getUserDetailsByUsername(String userName);
}
