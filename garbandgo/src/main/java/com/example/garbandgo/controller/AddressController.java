package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Address;
import com.example.garbandgo.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/addresses")  // Updated base path to make it unique
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public String index() {
        return "index";  // Path: /addresses
    }

    @GetMapping("/list")  // Updated mapping for listing addresses
    public String listAddress(Model model) {
        model.addAttribute("address", addressService.getAddressRepository());
        return "address";  // Path: /addresses/list
    }

    @PostMapping("/add")  // Updated mapping for adding an address
    public String addAddress(@ModelAttribute Address address) {
        addressService.saveAddress(address);
        return "redirect:/addresses/list";  // Redirects to /addresses/list
    }
}