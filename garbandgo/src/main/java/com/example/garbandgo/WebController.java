package com.example.garbandgo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String homePage() {
       System.out.println("Method homePage called");
       return "homePage"; // Трябва да съвпада с името на HTML файла
       }
}