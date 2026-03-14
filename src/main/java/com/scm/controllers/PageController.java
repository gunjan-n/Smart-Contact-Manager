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
}
