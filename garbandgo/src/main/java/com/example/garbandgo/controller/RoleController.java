package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Role;
import com.example.garbandgo.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/role")
    public String listRoles(Model model) {
        model.addAttribute("role", roleService.getRolesRepository());
        return "role";
    }

    @PostMapping("/addRoles")
    public String addRoles(@ModelAttribute Role role) {
        roleService.saveRoles(role);
        return "redirect:/role";
    }
}
