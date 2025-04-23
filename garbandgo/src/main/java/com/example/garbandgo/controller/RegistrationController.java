package com.example.garbandgo.controller;

import com.example.garbandgo.entities.User;
import com.example.garbandgo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }
}
