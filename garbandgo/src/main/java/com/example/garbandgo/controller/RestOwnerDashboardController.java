package com.example.garbandgo.controller;

import com.example.garbandgo.entities.User;
import com.example.garbandgo.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rest_owner")
public class RestOwnerDashboardController {

    private final UserRepository userRepository;

    public RestOwnerDashboardController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/restOwner")
    public String dashboard(Model model) {
        List<User> managers = userRepository.findAllByRoleName("MANAGER");
        model.addAttribute("managers", managers);
        return "rest_owner/restOwner";
    }
}
