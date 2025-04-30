package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Role;
import com.example.garbandgo.entities.User;
import com.example.garbandgo.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        user.setRole(new Role());
        model.addAttribute("user", user);
        return "users/register";
    }

    // GET /roles – може да върне някакъв index/начална страница за ролите
    @GetMapping
    public String index() {
        return "index";
    }

    // GET /roles/list – показва списък с роли
    @GetMapping("/list")
    public String listRoles(Model model) {
        model.addAttribute("role", roleService.getRolesRepository());
        return "role";
    }

    // POST /roles/add – обработва формата за добавяне на нова роля
    @PostMapping("/add")
    public String addRoles(@ModelAttribute Role role) {
        roleService.saveRoles(role);
        return "redirect:/roles/list";
    }
}
