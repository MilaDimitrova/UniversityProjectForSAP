package com.example.garbandgo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String homePage() {
        System.out.println("Method homePage called");
        return "homePage";
    }

    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "aboutUs";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "users/login";
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