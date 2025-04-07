package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Town;
import com.example.garbandgo.service.TownService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class TownController {
    public final TownService townService;

    public TownController(TownService townService) {
        this.townService = townService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/town")
    public String listTowns(Model model) {
        model.addAttribute("town", townService.getTownRepository());
        return "town";
    }

    @PostMapping("/addTown")
    public String addRoles(@ModelAttribute Town town) {
        townService.saveTown(town);
        return "redirect:/town";
    }
}
