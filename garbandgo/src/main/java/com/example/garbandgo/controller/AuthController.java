package com.example.garbandgo.controller;

import com.example.garbandgo.dto.UserRegistrationDTO;
import com.example.garbandgo.entities.User;
import com.example.garbandgo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());

        return ResponseEntity.ok(userService.registerUser(user, dto.getRole()));
    }
}
