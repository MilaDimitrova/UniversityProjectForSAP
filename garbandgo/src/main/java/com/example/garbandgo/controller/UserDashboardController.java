package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Order;
import com.example.garbandgo.entities.User;
import com.example.garbandgo.repositories.UserRepository;
import com.example.garbandgo.service.OrderService;
import com.example.garbandgo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserDashboardController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final OrderService orderService;

    public UserDashboardController(UserRepository userRepository, UserService userService, OrderService orderService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/userPage")
    public String userDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Потребителят не е намерен."));

        List<Order> userOrders = orderService.getOrdersByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("orders", userOrders);
        return "user/userPage";
    }

    @GetMapping("/order/{id}")
    public String viewUserOrder(@PathVariable("id") Integer id,
                                @AuthenticationPrincipal UserDetails userDetails,
                                Model model) {
        Order order = orderService.getOrderById(id).orElse(null);

        if (order == null || !order.getUser().getEmail().equals(userDetails.getUsername())) {
            return "redirect:/user/userPage?error";
        }

        model.addAttribute("order", order);
        model.addAttribute("orderProducts", order.getItems());
        return "user/order_view";
    }


    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("user") User updatedUser,
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
        return "redirect:/user/userPage?success";
    }
}
