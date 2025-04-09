package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Role;
import com.example.garbandgo.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/roles")  // Updated base path to make it unique
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String index() {
        return "index";  // Path: /roles
    }

    @GetMapping("/list")  // Updated mapping for listing roles
    public String listRoles(Model model) {
        model.addAttribute("role", roleService.getRolesRepository());
        return "role";  // Path: /roles/list
    }

    @PostMapping("/add")  // Updated mapping for adding roles
    public String addRoles(@ModelAttribute Role role) {
        roleService.saveRoles(role);
        return "redirect:/roles/list";  // Redirects to /roles/list
    }
}