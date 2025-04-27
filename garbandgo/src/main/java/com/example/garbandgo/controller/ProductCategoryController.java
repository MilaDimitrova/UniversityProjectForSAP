package com.example.garbandgo.controller;

import com.example.garbandgo.entities.ProductCategory;
import com.example.garbandgo.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("productCategories", productCategoryService.findAll());
        return "categories";
    }

    @PostMapping
    public String saveCategory(@ModelAttribute ProductCategory productCategory) {
        productCategoryService.save(productCategory);
        return "redirect:/categories";
    }
}
