package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Product;
import com.example.garbandgo.dto.ShoppingCart;
import com.example.garbandgo.service.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@SessionAttributes("shoppingCart")
public class CartController {

    private final ProductsService productsService;

    public CartController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @ModelAttribute("shoppingCart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }

    @GetMapping
    public String viewCart(@ModelAttribute("shoppingCart") ShoppingCart shoppingCart, Model model) {
        model.addAttribute("cartItems", shoppingCart.getItems());
        return "cart/viewCart";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Integer productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            @ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {
        Product product = productsService.getProductById(productId);
        shoppingCart.addItem(product, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Integer productId,
                                 @ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {
        shoppingCart.removeItem(productId);
        return "redirect:/cart";
    }
}
