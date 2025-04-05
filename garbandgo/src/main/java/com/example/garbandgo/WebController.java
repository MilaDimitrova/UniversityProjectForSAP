package com.example.garbandgo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String homePage() {
        System.out.println("Method homePage called");
        return "homePage"; // Трябва да съвпада с името на HTML файла
    }

    @GetMapping("/restaurants")
    public String ourRestaurants() {
        return "ourRestaurants";
    }

    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "aboutUs";
    }

    @GetMapping("/contact")
    public String contactUs() {
        return "contactUs";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profilePage";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registrationPage";
    }
}