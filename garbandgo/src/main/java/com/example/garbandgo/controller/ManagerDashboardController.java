package com.example.garbandgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerDashboardController {
    @GetMapping("/manager/managerPage")
    public String dashboard() {
        return "manager/managerPage"; // managerPage.html
    }
}
