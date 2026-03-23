package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.helpers.Helper;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    // user Dashboard page
    @GetMapping("/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    // user Profile page
    @GetMapping("/profile")
    public String userProfile(Authentication authentication){

        String username = Helper.getEmailOfLoggedInUser(authentication);

        logger.info("user loggegIn : {}", username);
        return "user/profile";
    }

}
