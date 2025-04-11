package com.example.garbandgo.controller;

import com.example.garbandgo.entities.ProductCategory;
import com.example.garbandgo.service.ProductCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productcategories")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/list")
    public String listProductCategories(Model model) {
        model.addAttribute("productCategories", productCategoryService.getProductCategoryRepository());
        return "productcategories";
    }

    @PostMapping("/add")
    public String addProductCategory(@ModelAttribute ProductCategory productCategory) {
        productCategoryService.saveProductCategory(productCategory);
        return "redirect:/productcategories/list";
    }
}
