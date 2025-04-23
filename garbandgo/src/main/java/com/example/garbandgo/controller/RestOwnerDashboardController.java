package com.example.garbandgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestOwnerDashboardController {
    @GetMapping("/rest_owner/restOwner")
    public String dashboard() {
        return "rest_owner/restOwner"; // restOwner.html
    }
}
