package com.mikejuliet.java_backend.Controllers;

import com.mikejuliet.java_backend.entities.BankDetails;
import com.mikejuliet.java_backend.entities.UserDetails;
import com.mikejuliet.java_backend.entities.pojos.LoginRequest;
import com.mikejuliet.java_backend.payload.ApiResponse;
import com.mikejuliet.java_backend.services.service_impl.BankAccountDetailsServiceImpl;
import com.mikejuliet.java_backend.services.service_impl.UserDetailServiceImpl;
import com.mikejuliet.java_backend.services.service_impl.UserOrchestrationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banking-backend/")
public class BackendController {

    @Autowired
    UserDetailServiceImpl userService;
    @Autowired
    UserOrchestrationServiceImpl orchestrationService;
    @Autowired
    BankAccountDetailsServiceImpl bankService;


    public static ApiResponse response = new ApiResponse();

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody UserDetails userDetails) {

        try {
            UserDetails existingUser = userService.getUserDetailsByUsername(userDetails.getUserName());
            if(existingUser != null){// checking weather the user does not exist
                return ResponseEntity.status(HttpStatus.CONFLICT).body(existingUser);
            }
            else {
                UserDetails createdUser = userService.saveUsersDetails(userDetails);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
            }

        } catch (Exception e) {
            String errorMessage = "User creation failed. Please check the input data.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginIntoUserAccount(@RequestBody LoginRequest loginRequest,HttpSession session){

            if(orchestrationService.userLogin(loginRequest.getUsername(), loginRequest.getPassword())){

                session.setAttribute("user-details",userService.getUserDetailsByUsername(loginRequest.getUsername()));
                response.setMessage("login successfull");
                response.setStatus(HttpStatus.OK);
                response.setSuccess(true);
                ApiResponse.builder().build();
                return ResponseEntity.status(201).body(response);
            }
            else {
                response.setSuccess(false);
                response.setMessage("unauthorized");
                response.setStatus(HttpStatus.UNAUTHORIZED);
                ApiResponse.builder().build();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

    }

    @PostMapping("/add-back-ac-detail")
    public ResponseEntity<BankDetails> saveBankDetails(@RequestBody BankDetails bankDetails, HttpServletRequest request){
        String userName = (String) request.getSession().getAttribute("userName");
        return ResponseEntity.status(201).body(bankService.saveBankAccountDetails(userName,bankDetails));
    }
    @GetMapping("/get-userDetails/{username}")
    public ResponseEntity<UserDetails> getUserDetails(@PathVariable("username") String username){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserDetailsByUsername(username));
    }

    @PostMapping("/log-out")
    public ResponseEntity<ApiResponse> logout(HttpSession session){
        session.invalidate();
        response.setStatus(HttpStatus.OK);
        response.setMessage("session been invalidated");
        response.setSuccess(true);
        ApiResponse.builder().build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
