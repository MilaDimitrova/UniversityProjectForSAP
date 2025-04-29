package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Product;
import com.example.garbandgo.entities.ProductCategory;
import com.example.garbandgo.entities.Restaurant;
import com.example.garbandgo.service.ProductsService;
import com.example.garbandgo.service.ProductCategoryService;
import com.example.garbandgo.repositories.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;
    private final RestaurantRepository restaurantRepository;
    private final ProductCategoryService productCategoryService;

    public ProductsController(ProductsService productsService,
                              RestaurantRepository restaurantRepository,
                              ProductCategoryService productCategoryService) {
        this.productsService = productsService;
        this.restaurantRepository = restaurantRepository;
        this.productCategoryService = productCategoryService;
    }

    // Управление на продукти за конкретен ресторант
    @GetMapping("/manage/{restaurantId}")
    public String manageProducts(@PathVariable Integer restaurantId, Model model) {
        List<Product> products = productsService.findProductsByRestaurantId(restaurantId);
        List<ProductCategory> categories = productCategoryService.findAll();
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        model.addAttribute("products", products);
        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("categories", categories);
        model.addAttribute("newProduct", new Product());
        model.addAttribute("restaurantName", restaurant.getRestaurant());
        return "products/manageProducts";
    }

    @PostMapping("/add/{restaurantId}")
    public String addProduct(@PathVariable Integer restaurantId,
                             @ModelAttribute("newProduct") Product product) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        product.setRestaurant(restaurant);

        if (product.getCategory() == null || product.getCategory().getId() == null) {
            throw new IllegalArgumentException("Product category is required");
        }
        ProductCategory persistentCategory = productCategoryService.findById(product.getCategory().getId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        product.setCategory(persistentCategory);

        productsService.saveProduct(product);
        return "redirect:/products/manage/" + restaurantId;
    }

    @PostMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Integer productId,
                                @RequestParam Integer restaurantId) {
        productsService.deleteProductById(productId);
        return "redirect:/products/manage/" + restaurantId;
    }

    @GetMapping("/edit/{productId}")
    public String editProductForm(@PathVariable Integer productId, Model model) {
        Product product = productsService.getProductById(productId);
        List<ProductCategory> categories = productCategoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "products/editProducts";
    }

    @PostMapping("/edit/{productId}")
    public String updateProduct(@PathVariable Integer productId,
                                @ModelAttribute("product") Product product) {
        Product existingProduct = productsService.getProductById(productId);

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setDeliveryPrice(product.getDeliveryPrice());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setCurrency(product.getCurrency());

        if (product.getCategory() != null && product.getCategory().getId() != null) {
            ProductCategory persistentCategory = productCategoryService.findById(product.getCategory().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            existingProduct.setCategory(persistentCategory);
        }

        productsService.saveProduct(existingProduct);
        return "redirect:/products/manage/" + existingProduct.getRestaurant().getId();
    }

    @GetMapping("/shop")
    public String showProductsGroupedByRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        Map<Restaurant, List<Product>> groupedProducts = new LinkedHashMap<>();

        for (Restaurant restaurant : restaurants) {
            List<Product> products = productsService.findProductsByRestaurantId(restaurant.getId());
            groupedProducts.put(restaurant, products);
        }
        model.addAttribute("groupedProducts", groupedProducts);
        return "products/shop";
    }

    @GetMapping("/shop/{restaurantId}")
    public String showProductsForRestaurant(@PathVariable Integer restaurantId, Model model) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        List<Product> products = productsService.findProductsByRestaurantId(restaurantId);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("products", products);

        return "products/shopByRestaurant"; // създай отделен Thymeleaf шаблон ако искаш за конкретен ресторант
    }
}
