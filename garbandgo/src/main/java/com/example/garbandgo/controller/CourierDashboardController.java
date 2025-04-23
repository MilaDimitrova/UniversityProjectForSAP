package com.example.garbandgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourierDashboardController {
    @GetMapping("/courier/deliveryPage")
    public String dashboard() {
        return "courier/deliveryPage"; // deliveryPage.html
    }
}
