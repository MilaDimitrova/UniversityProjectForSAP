package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Product;
import com.example.garbandgo.service.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ShopController {
    private final ProductsService productsService;

    public ShopController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/shop")
    public String shopProducts(Model model) {
        List<Product> products = productsService.findAllProducts();
        model.addAttribute("products", products);
        return "products/shop";
    }
}
