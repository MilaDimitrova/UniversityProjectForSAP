package com.example.garbandgo.controller;

import com.example.garbandgo.entities.ContactMessage;
import com.example.garbandgo.repositories.ContactMessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ContactController {

    private final ContactMessageRepository contactMessageRepository;

    public ContactController(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @PostMapping("/contactUs")
    public String submitContactForm(@ModelAttribute ContactMessage message) {
        contactMessageRepository.save(message);
        return "redirect:/contactUs?success";
    }
}
