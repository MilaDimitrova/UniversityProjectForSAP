package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Product;
import com.example.garbandgo.service.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    // Ако не ви е нужен методът index(), може да го премахнете или оставете за други цели.
    @GetMapping
    public String index() {
        return "index";
    }

    // Методът за зареждане на списъка с продукти – името на view е "productsToCart" (кореспондира на productsToCart.html)
    @GetMapping("/list")
    public String listProducts(Model model) {
        model.addAttribute("products", productsService.getProductRepository());
        return "productsToCart";
    }

    // Метод за добавяне на продукт
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productsService.saveProduct(product);
        return "redirect:/products/list";
    }
}
