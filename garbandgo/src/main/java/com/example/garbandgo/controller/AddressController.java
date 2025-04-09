package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Address;
import com.example.garbandgo.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/address")
    public String listAddress(Model model) {
        model.addAttribute("address", addressService.getAddressRepository());
        return "address";
    }

    @PostMapping("/addAddress")
    public String addAddress(@ModelAttribute Address address) {
        addressService.saveAddress(address);
        return "redirect:/address";
    }
}
