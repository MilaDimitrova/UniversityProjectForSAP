package com.example.garbandgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDashboardController {

    @GetMapping("/user/userPage")
    public String userDashboard() {
        return "user/userPage"; // 👈 Път до templates/user/dashboard.html
    }
}
