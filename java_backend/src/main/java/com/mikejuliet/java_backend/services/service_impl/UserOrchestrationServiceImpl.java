package com.mikejuliet.java_backend.services.service_impl;

import com.mikejuliet.java_backend.entities.UserDetails;
import com.mikejuliet.java_backend.services.UserOrchestrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.mikejuliet.java_backend.services.service_impl.UserDetailServiceImpl.*;

@Service
public class UserOrchestrationServiceImpl implements UserOrchestrationService {
    @Autowired
    private UserDetailServiceImpl userService;


    @Override
    public boolean userLogin(String username, String password) {
        String encodedPassword = encodePassword(password);
        UserDetails foundUser = userService.getUserDetailsByUsername(username);
        boolean result = encodedPassword.equals(foundUser.getPassword());
        if(foundUser!=null && result == true){
            return true;
        }
        else return false;
    }
}
