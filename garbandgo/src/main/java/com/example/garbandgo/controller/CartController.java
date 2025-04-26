package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Product;
import com.example.garbandgo.dto.ShoppingCart;
import com.example.garbandgo.service.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("totalPrice", shoppingCart.getTotalPrice());
        return "cart/viewCart";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Integer productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            @ModelAttribute("shoppingCart") ShoppingCart shoppingCart,
                            RedirectAttributes redirectAttributes) {
        Product product = productsService.getProductById(productId);
        if (product != null) {
            shoppingCart.addItem(product, quantity);
            redirectAttributes.addFlashAttribute("successMessage", "Продуктът беше добавен в количката.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Продуктът не беше намерен.");
        }
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Integer productId,
                                 @ModelAttribute("shoppingCart") ShoppingCart shoppingCart,
                                 RedirectAttributes redirectAttributes) {
        shoppingCart.removeItem(productId);
        redirectAttributes.addFlashAttribute("successMessage", "Продуктът беше премахнат от количката.");
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute("shoppingCart") ShoppingCart shoppingCart,
                           RedirectAttributes redirectAttributes) {
        if (shoppingCart.getItems().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Количката ви е празна.");
            return "redirect:/cart";
        }


        shoppingCart.clear();

        redirectAttributes.addFlashAttribute("successMessage", "Поръчката е приета успешно!");
        return "redirect:/products/shop"; // Или към страница за потвърждение
    }
}
