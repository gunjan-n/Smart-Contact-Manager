package com.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scm.entities.Contact;
import com.scm.entities.User;

public interface ContactService {
    // save contact
    Contact save(Contact contact);
    // update contact
    Contact update(Contact contact);
    // get all contacts
    List<Contact> getAllContacts();

    // get contact by id
    Contact getContactById(String id);

    // delete contact
    void delete(String id);

    // search contact
    List<Contact> search(String name, String email, String phoneNumber);

    // get all contact by user id
    List<Contact> getContactsByUserId(String userId);

    Page<Contact> getContactsByUser(User user, int page, int size, String sortBy, String direction);

}
