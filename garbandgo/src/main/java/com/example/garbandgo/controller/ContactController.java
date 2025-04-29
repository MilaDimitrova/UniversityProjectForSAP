package com.example.garbandgo.controller;

import com.example.garbandgo.entities.ContactMessage;
import com.example.garbandgo.repositories.ContactMessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {

    private final ContactMessageRepository contactMessageRepository;

    public ContactController(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        return "contactUs";
    }

    @PostMapping("/contact")
    public String submitContactForm(@ModelAttribute ContactMessage message) {
        contactMessageRepository.save(message);
        return "redirect:/contact?success";
    }
}
