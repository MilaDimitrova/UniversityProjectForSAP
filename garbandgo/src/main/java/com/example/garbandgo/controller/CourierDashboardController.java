package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Order;
import com.example.garbandgo.entities.User;
import com.example.garbandgo.repositories.OrderRepository;
import com.example.garbandgo.repositories.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/courier")
public class CourierDashboardController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public CourierDashboardController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/deliveryPage")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User courier = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Потребителят не е намерен."));

        List<Order> availableOrders = orderRepository.findByDeliveredByIsNull();
        List<Order> myDeliveries = orderRepository.findByDeliveredBy(courier);

        model.addAttribute("availableOrders", availableOrders);
        model.addAttribute("myDeliveries", myDeliveries);
        return "courier/deliveryPage";
    }

    @PostMapping("/accept")
    public String acceptOrder(@RequestParam("orderId") Integer orderId,
                              @AuthenticationPrincipal UserDetails userDetails) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Поръчката не е намерена."));

        User courier = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Потребителят не е намерен."));

        order.setDeliveredBy(courier);
        order.setDeliveredAt(LocalDateTime.from(Instant.now()));
        orderRepository.save(order);

        return "redirect:/courier/deliveryPage";
    }

    @PostMapping("/delivered")
    public String markAsDelivered(@RequestParam("orderId") Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Поръчката не е намерена."));

        order.setDeliveredAt(LocalDateTime.from(Instant.now()));
        orderRepository.save(order);

        return "redirect:/courier/deliveryPage";
    }
}
