package com.example.quan.service;

import com.example.quan.models.Contact;

public interface ContactService {
    Iterable<Contact> findAll();

    Contact findOne(Long id);

    void saveContact(Contact contact);

    void remove(Long id);
}
