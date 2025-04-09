package com.example.garbandgo.controller;

import com.example.garbandgo.entities.ProductIngredient;
import com.example.garbandgo.service.ProductIngredientsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productingredients")
public class ProductIngredientsController {
    private final ProductIngredientsService productIngredientsService;

    public ProductIngredientsController(ProductIngredientsService productIngredientsService) {
        this.productIngredientsService = productIngredientsService;
    }

    @GetMapping
    public String index() {
        return "index";  // Начална страница.
    }

    @GetMapping("/list")
    public String listProductIngredients(Model model) {
        model.addAttribute("productIngredients", productIngredientsService.getProductIngrediantsRepository());
        return "productingredients";
    }

    @PostMapping("/add")
    public String addProductIngredients(@ModelAttribute ProductIngredient productIngredient) {
        productIngredientsService.saveProductIngredient(productIngredient);
        return "redirect:/productingredients/list";
    }
}
