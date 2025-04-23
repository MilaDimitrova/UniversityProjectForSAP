package com.example.garbandgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {
    @GetMapping("/admin/rootPage")
    public String dashboard() {
        return "admin/rootPage"; // rootPage.html
    }
}

