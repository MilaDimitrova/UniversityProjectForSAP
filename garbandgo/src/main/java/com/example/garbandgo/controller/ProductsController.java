package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Product;
import com.example.garbandgo.entities.Restaurant;
import com.example.garbandgo.service.ProductsService;
import com.example.garbandgo.repositories.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;
    private final RestaurantRepository restaurantRepository;

    public ProductsController(ProductsService productsService, RestaurantRepository restaurantRepository) {
        this.productsService = productsService;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/manage/{restaurantId}")
    public String manageProducts(@PathVariable Integer restaurantId, Model model) {
        List<Product> products = productsService.findProductsByRestaurantId(restaurantId);
        model.addAttribute("products", products);
        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("newProduct", new Product());
        return "products/manageProducts";
    }

    @PostMapping("/add/{restaurantId}")
    public String addProduct(@PathVariable Integer restaurantId, @ModelAttribute Product product) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        product.setRestaurant(restaurant);
        productsService.saveProduct(product);
        return "redirect:/products/manage/" + restaurantId;
    }

    @PostMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Integer productId, @RequestParam Integer restaurantId) {
        productsService.deleteProductById(productId);
        return "redirect:/products/manage/" + restaurantId;
    }

    @GetMapping("/edit/{productId}")
    public String editProductForm(@PathVariable Integer productId, Model model) {
        Product product = productsService.findProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        model.addAttribute("product", product);
        return "products/editProduct";
    }
}
