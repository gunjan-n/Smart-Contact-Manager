package com.scm.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.forms.ContactSearchForm;
import com.scm.helpers.Helper;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.ContactService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    private Path path;

    private Page<Contact> pageContact;

    @GetMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);

        return "user/add_contact";
    }

    @PostMapping("/add")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult, Authentication authentication, HttpSession session){
        // process contact form data
        System.out.println(contactForm);

        // validate form
        if (bindingResult.hasErrors()) {
            Message errorMessage = Message.builder().content("Please correct the following errors").type(MessageType.red).build();

            // add the alert message
            session.setAttribute("message", errorMessage);
            return "user/add_contact";
        }

        try{
            String username = Helper.getEmailOfLoggedInUser(authentication);
            User user = userService.getUserByEmail(username);
            
            Contact contact = new Contact();

            // process the contact picture
            if (contactForm.getContactImage().isEmpty()) {
                contact.setPicture("userImage.png");
            } else {
                // upload the file to folder
                contact.setPicture(contactForm.getContactImage().getOriginalFilename());

                File saveFile = new ClassPathResource("static/images").getFile();
                path = Paths.get(saveFile.getAbsolutePath()+File.separator+contactForm.getContactImage().getOriginalFilename());
                Files.copy(contactForm.getContactImage().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                
            }
            contact.setName(contactForm.getName());
            contact.setFavorite(contactForm.isFavorite());
            contact.setEmail(contactForm.getEmail());
            contact.setPhoneNumber(contactForm.getPhoneNumber());
            contact.setAddress(contactForm.getAddress());
            contact.setDescription(contactForm.getDescription());
            contact.setLinkedInLink(contactForm.getLinkedInLink());
            contact.setWebsiteLink(contactForm.getWebsiteLink());
            contact.setUser(user);

            contactService.save(contact);

            Message message = Message.builder().content("Contact Saved Successfuly").type(MessageType.green).build();

            // add the alert message
            session.setAttribute("message", message);
        } catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/user/contacts/add";
    }

    // view contacts page
    @GetMapping
    public String viewContacts(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "sortBy", defaultValue = "name") String sortBy, @RequestParam(value = "direction", defaultValue = "asce") String direction, Model model, Authentication authentication){
        // gett user's all contact
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        Page<Contact> pageContacts = contactService.getContactsByUser(user, page, size, sortBy, direction);

        model.addAttribute("contactSearchForm", new ContactSearchForm());
        model.addAttribute("pageContacts", pageContacts);
        return "user/contacts";
    }

    // search handler
    @GetMapping("/search")
    public String searchHandler(@ModelAttribute ContactSearchForm contactSearchForm, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "sortBy", defaultValue = "name") String sortBy, @RequestParam(value = "direction", defaultValue = "asce") String direction, Model model, Authentication authentication){

        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        Page<Contact> pageContact = null;
        if (contactSearchForm.getField().equalsIgnoreCase("name")) {
            pageContact = contactService.searchByName(user, contactSearchForm.getKeyword(), size, page, sortBy, direction);
        }
        else if (contactSearchForm.getField().equalsIgnoreCase("email")) {
            pageContact = contactService.searchByEmail(user, contactSearchForm.getKeyword(), size, page, sortBy, direction);
        }
        else if (contactSearchForm.getField().equalsIgnoreCase("phone")) {
            pageContact = contactService.searchByPhoneNumber(user, contactSearchForm.getKeyword(), size, page, sortBy, direction);
        }

        model.addAttribute("contactSearchForn", contactSearchForm);
        model.addAttribute("pageContacts", pageContact);
        return "user/search";
    }
}
