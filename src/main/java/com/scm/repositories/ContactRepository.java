package com.scm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scm.entities.Contact;
import com.scm.entities.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    // find all contacts of a user
    // this will be automatically provided because is a field in contact entity
    List<Contact> findByUser(User user);

    // give query for this, usersId is not a field in contact entity
    @Query("SELECT c FROM CONTACT c WHERE c.user.id =:userId")
    List<Contact> findByUserId(String userId);
}
