package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Country;
import com.example.garbandgo.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/country")
    public String listCountry(Model model) {
        model.addAttribute("country", countryService.getCountryRepository());
        return "country";
    }

    @PostMapping("/addCountry")
    public String addCountry(@ModelAttribute Country country) {
        countryService.saveAddress(country);
        return "redirect:/country";
    }
}
