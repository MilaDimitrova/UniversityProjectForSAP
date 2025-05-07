package com.example.garbandgo.controller;

import com.example.garbandgo.entities.User;
import com.example.garbandgo.repositories.UserRepository;
import com.example.garbandgo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminDashboardController {

    private final UserRepository userRepository;
    private final UserService userService;

    public AdminDashboardController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/admin/rootPage")
    public String adminDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Потребителят не е намерен."));
        model.addAttribute("user", user);
        return "admin/rootPage";
    }

    @PostMapping("/admin/update")
    public String updateAdminProfile(@ModelAttribute("user") User updatedUser,
                                     @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Потребителят не е намерен."));
        if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty()) {
            user.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            String encodedPassword = userService.encodePassword(updatedUser.getPassword());
            user.setPassword(encodedPassword);
        }
        userRepository.save(user);
        return "redirect:/admin/rootPage?success";
    }

    @PostMapping("/admin/register-user")
    public String registerUserFromDashboard(@RequestParam String username,
                                            @RequestParam String email,
                                            @RequestParam String password,
                                            @RequestParam String phone,
                                            @RequestParam String role,
                                            RedirectAttributes redirectAttributes) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);

            userService.registerUser(user, role);
            redirectAttributes.addFlashAttribute("success", "Потребителят е добавен успешно!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Грешка: " + e.getMessage());
        }

        return "redirect:/admin/rootPage";
    }
}
