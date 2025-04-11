package com.example.garbandgo.controller;

import com.example.garbandgo.entities.User;
import com.example.garbandgo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")  // Unique base path to resolve ambiguity
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index() {
        return "index";  // Path: /users
    }

    @GetMapping("/list")  // Updated mapping for listing users
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsersRepository());
        return "users";  // Path: /users/list
    }

    @PostMapping("/add")  // Updated mapping for adding users
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users/list";  // Redirects to /users/list
    }
}
