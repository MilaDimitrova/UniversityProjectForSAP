package com.example.garbandgo.controller;

import com.example.garbandgo.entities.User;
import com.example.garbandgo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/users/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User()); // Нов обект за регистрация
        return "users/register";
    }

    @GetMapping("/users/login")
    public String showLoginPage() {
        return "users/login";
    }
}
