package com.example.quan.service.impl;

import com.example.quan.models.Contact;
import com.example.quan.repository.ContactRepository;
import com.example.quan.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Iterable<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findOne(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void remove(Long id) {
        contactRepository.deleteById(id);
    }
}
