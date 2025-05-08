package com.example.garbandgo.controller;

import com.example.garbandgo.dto.PromocodeDTO;
import com.example.garbandgo.dto.RestaurantWithFullData;
import com.example.garbandgo.entities.Promocode;
import com.example.garbandgo.service.PromocodeService;
import com.example.garbandgo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/promocodes")
public class PromocodeController {

    private final PromocodeService promocodeService;
    private final RestaurantService restaurantService;

    @Autowired
    public PromocodeController(PromocodeService promocodeService, RestaurantService restaurantService) {
        this.promocodeService = promocodeService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("")
    public String index(Model model) {
        List<PromocodeDTO> promocodes = promocodeService.getAllPromocodes();
        model.addAttribute("promocodes", promocodes);
        return "promocodes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<PromocodeDTO> promocode = promocodeService.getPromocodeById(id);
        if (promocode.isPresent()) {
            model.addAttribute("promocode", promocode.get());
            return "promocodes/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Promocode not found");
        }
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("promocode", new PromocodeDTO());
        model.addAttribute("restaurants", restaurantService.findAll());
        return "promocodes/form";
    }

    @PostMapping
    public String save(@ModelAttribute("promocode") PromocodeDTO promocodeDTO, @RequestParam("restaurantID") Integer restaurantId) {
        promocodeService.saveDTO(promocodeDTO, restaurantId, Optional.ofNullable(promocodeDTO.getId() != null ? promocodeDTO.getId() : null));

        return "redirect:/promocodes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<PromocodeDTO> promocodeDTO = promocodeService.getPromocodeById(id);
        if (promocodeDTO.isPresent()) {
            model.addAttribute("promocode", promocodeDTO.get());
            model.addAttribute("restaurants", restaurantService.findAll());
            return "promocodes/form";
        }
        return "redirect:/promocodes/";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePromocode(@PathVariable Integer id) {
        promocodeService.deleteById(id);
        return "redirect:/promocodes";
    }
}