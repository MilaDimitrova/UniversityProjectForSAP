package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Order;
import com.example.garbandgo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Показва списъка с поръчки, направени от днес
    @GetMapping("/index")
    public String index(Model model) {
        LocalDate today = LocalDate.now();
        List<Order> orders = orderService.getOrdersCreatedAfter(today.atStartOfDay());
        model.addAttribute("orders", orders);
        return "Orders/order_list";
    }

    @GetMapping
    public String redirectToIndex() {
        return "redirect:/orders/index";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("order", new Order());
        return "Orders/order_form";
    }

    @PostMapping
    public String save(@ModelAttribute("order") Order order) {
        order.setOrderDate(LocalDateTime.now());
        order.setCancelled(new byte[]{0});
        order.setStatus("PENDING");
        orderService.saveOrder(order);
        return "redirect:/orders/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Order order = orderService.getOrderById(id).orElse(null);
        if (order != null) {
            model.addAttribute("order", order);
            return "Orders/order_form";
        }
        return "redirect:/orders/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        orderService.deleteOrder(id);
        return "redirect:/orders/index";
    }

    @PostMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable("id") int id) {
        orderService.cancelOrder(id);
        return "redirect:/orders/index";
    }

    @PostMapping("/deliver/{id}")
    public String markAsDelivered(@PathVariable("id") int id) {
        orderService.markAsDelivered(id); // вече без UserDetails
        return "redirect:/orders/index";
    }
}
