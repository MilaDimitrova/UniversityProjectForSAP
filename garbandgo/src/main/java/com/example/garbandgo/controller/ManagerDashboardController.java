package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Restaurant;
import com.example.garbandgo.entities.User;
import com.example.garbandgo.repositories.RestaurantRepository;
import com.example.garbandgo.repositories.UserRepository;
import com.example.garbandgo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manager")
public class ManagerDashboardController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public ManagerDashboardController(UserService userService,
                                      UserRepository userRepository,
                                      RestaurantRepository restaurantRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/managerPage")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Потребителят не е намерен."));

        Restaurant restaurant = restaurantRepository.findByManager(user).orElse(null);

        model.addAttribute("user", user);
        model.addAttribute("restaurant", restaurant);
        return "manager/managerPage";
    }

    @PostMapping("/manager/update")
    public String updateManagerProfile(@ModelAttribute("user") User updatedUser,
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
        return "redirect:/manager/managerPage?success";
    }

    @PostMapping("/addCourier")
    public String addCourier(@RequestParam String username,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String phone) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);

        userService.registerUser(user, "COURIER");

        return "redirect:/manager/rootPage?courierAdded";
    }

}
