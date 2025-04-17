package com.example.garbandgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login"; // Увери се, че login.html е в templates/users/
    }

    @PostMapping("/login")
    public String processLogin() {
        return "redirect:/home";
    }
}
