package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {
    
    @GetMapping("/")
    public String homePage(){
        System.out.println("Home Page");
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(){
        System.out.println("About Page");
        return "about";
    }

    @GetMapping("/services")
    public String servicesPage(){
        System.out.println("Services Page");
        return "services";
    }

    @GetMapping("/contact")
    public String contactPage(){
        System.out.println("Contact Page");
        return "contact";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("Login Page");
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        System.out.println("Signup Page");
        return "register";
    }
}
