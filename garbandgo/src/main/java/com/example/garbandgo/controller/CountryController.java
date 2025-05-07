package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Country;
import com.example.garbandgo.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/countries")  // Unique base path
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String index() {
        return "Country_index";
    }

    @GetMapping("/list")
    public String listCountry(Model model) {
        model.addAttribute("country", countryService.getCountryRepository());
        return "country";
    }

    @PostMapping("/add")
    public String addCountry(@ModelAttribute Country country) {
        countryService.saveCountry(country);
        return "redirect:/countries";
    }
}