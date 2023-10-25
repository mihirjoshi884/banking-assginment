package com.mikejuliet.java_backend.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BankingWebController {
    @RequestMapping("/banking-web/home")
    public String home(){
        return "home.jsp";
    }

    @RequestMapping("/banking-web/login")
    public String login(){
        return "login.jsp";
    }

    @RequestMapping("/banking-web/signUp")
    public String signUp(){
        return "signUp.jsp";
    }

    @RequestMapping("/banking-web/dashborad")
    public String dashboard(){ return "dashboard.jsp";}
}
