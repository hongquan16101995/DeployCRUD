package com.example.quan.controller;

import com.example.quan.models.Category;
import com.example.quan.models.Contact;
import com.example.quan.service.CategoryService;
import com.example.quan.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    public Environment environment;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping
    public ModelAndView listContact(){
        return new ModelAndView("list", "contacts", contactService.findAll());
    }

    @GetMapping("/create-contact")
    public ModelAndView createContact(){
        return new ModelAndView("create", "contact", new Contact());
    }

    @PostMapping("/create-contact")
    public ModelAndView saveContact(@ModelAttribute("contact") Contact contact){
        MultipartFile file = contact.getAvatar();
        String image = file.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.path").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        contact.setImage(image);
        contactService.saveContact(contact);
        return new ModelAndView("list", "contacts", contactService.findAll());
    }

    @GetMapping("/edit-contact/{id}")
    public ModelAndView editContact(@PathVariable("id") long id){
        Contact contact = contactService.findOne(id);
        return new ModelAndView("edit", "contact", contact);
    }

    @PostMapping("/edit-contact/{id}")
    public ModelAndView updateContact(Contact contact){
        contactService.saveContact(contact);
        return new ModelAndView("list", "contacts", contactService.findAll());
    }

    @GetMapping("/view-contact/{id}")
    public ModelAndView viewContact(@PathVariable("id") long id){
        return new ModelAndView("view", "contact", contactService.findOne(id));
    }

    @GetMapping("/delete-contact/{id}")
    public ModelAndView deleteContact(@PathVariable("id") long id){
        contactService.remove(id);
        return new ModelAndView("list", "contacts", contactService.findAll());
    }
}
