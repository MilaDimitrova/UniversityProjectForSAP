package com.example.garbandgo.controller;

import com.example.garbandgo.entities.User;
import com.example.garbandgo.repositories.UserRepository;
import com.example.garbandgo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserDashboardController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserDashboardController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/userPage")
    public String userDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        model.addAttribute("user", user);
        return "user/userPage";
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("user") User updatedUser,
                                @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Потребителят не е намерен."));

        // Актуализиране на username
        if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty()) {
            user.setUsername(updatedUser.getUsername());
        }

        // Актуализиране на password
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            String encodedPassword = userService.getPasswordEncoder().encode(updatedUser.getPassword());
            user.setPassword(encodedPassword);
        }

        userRepository.save(user);
        return "redirect:/user/userPage?success";
    }
}
