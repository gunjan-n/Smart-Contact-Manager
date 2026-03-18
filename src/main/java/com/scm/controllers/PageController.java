package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


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
    public String registerUser(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session){

        // System.out.println("userForm : "+userForm);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        // User user = User.builder().name(userForm.getName()).email(userForm.getEmail()).password(userForm.getPassword()).about(userForm.getAbout()).phoneNumber(userForm.getPhoneNumber()).profilePic("").build();
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("");
        // System.out.println("user : " + user);

        User savedUser = userService.saveUser(user);

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        // add the alert message
        session.setAttribute("message", message);

        return "redirect:/register";
    }
}
