package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;


@Controller
public class PageController {

    @Autowired
    private UserService userService;
    
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
    public String register(Model modle){
        UserForm userForm = new UserForm();
        modle.addAttribute("userForm", userForm);
        System.out.println("Signup Page");
        return "register";
    }

    // processing user registration
    @PostMapping("/register-user")
    public String registerUser(@ModelAttribute UserForm userForm){

        // User user = User.builder().name(userForm.getName()).email(userForm.getEmail()).password(userForm.getPassword()).about(userForm.getAbout()).phoneNumber(userForm.getPhoneNumber()).profilePic("").build();
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("");
        User savedUser = userService.saveUser(user);
        return "redirect:/register";
    }
}
