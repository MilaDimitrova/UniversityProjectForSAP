package com.example.garbandgo.controller;

import com.example.garbandgo.entities.User;
import com.example.garbandgo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index() {
        return "index";
    }


    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsersRepository());
        return "users";
    }


    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users";

    }
}
