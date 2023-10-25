package com.mikejuliet.java_backend.services.service_impl;

import com.mikejuliet.java_backend.entities.UserDetails;
import com.mikejuliet.java_backend.repositories.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;



@Service
public class UserDetailServiceImpl implements com.mikejuliet.java_backend.services.UserDetailService {

    @Autowired
    UserDetailRepository userRepo;

    private static String encodedPassword;
    public static   String encodePassword(String password){
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Convert the password string to bytes
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

            // Compute the hash
            byte[] hashBytes = md.digest(passwordBytes);

            // Convert the hash to a hexadecimal representation
            StringBuilder hexHash = new StringBuilder();
            for (byte hashByte : hashBytes) {
                hexHash.append(String.format("%02x", hashByte));
            }

            encodedPassword = hexHash.toString();
            return encodedPassword;
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception appropriately
            e.printStackTrace();

        }
        return null;
    }
    @Override
    public UserDetails saveUsersDetails(UserDetails userDetails) {
        // generating unique user ids
        String userId = UUID.randomUUID().toString();
        //encoding password and storing it to the database
        encodePassword(userDetails.getPassword());
        userDetails.setPassword(encodedPassword);
        userDetails.setUserId(userId);
        return userRepo.save(userDetails);
    }

    @Override
    public UserDetails getUserDetailsByUsername(String userName) {
        return userRepo.findUserDetailsByUserName(userName);
    }
}
