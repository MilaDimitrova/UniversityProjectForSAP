package com.example.garbandgo;

import com.example.garbandgo.entities.ContactMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String homePage() {
        System.out.println("Method homePage called");
        return "homePage"; // Трябва да съвпада с името на HTML файла
    }

    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "aboutUs";
    }


    @GetMapping("/contact")
    public String contactForm(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        return "contactUs";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "users/login"; // Ако login.html е профилната страница
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "register";
    }

    @GetMapping("/users/rootPage")
    public String rootPageProfile() {
        return "rootPage";
    }


}