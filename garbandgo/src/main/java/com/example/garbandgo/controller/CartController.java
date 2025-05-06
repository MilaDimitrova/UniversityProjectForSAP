package com.example.garbandgo.controller;

import com.example.garbandgo.dto.CartItem;
import com.example.garbandgo.dto.ShoppingCart;
import com.example.garbandgo.entities.Order;
import com.example.garbandgo.entities.OrderProduct;
import com.example.garbandgo.entities.Product;
import com.example.garbandgo.service.OrderService;
import com.example.garbandgo.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.garbandgo.repositories.UserRepository;
import com.example.garbandgo.entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
@SessionAttributes("shoppingCart")
public class CartController {

    private final ProductsService productsService;

    @Autowired
    private OrderService orderService;

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

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute("shoppingCart") ShoppingCart shoppingCart,
                           @AuthenticationPrincipal UserDetails userDetails,
                           RedirectAttributes redirectAttributes) {
        if (shoppingCart.getItems().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Количката ви е празна.");
            return "redirect:/cart";
        }

        User customer = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Потребителят не е намерен."));

        Order order = new Order();
        LocalDateTime now = LocalDateTime.now();

        order.setOrderDate(now);
        order.setCancelled(new byte[]{0});
        order.setStatus("PENDING");
        order.setTotalPrice(shoppingCart.getTotalPrice());
        order.setAdditionalDiscount(0.0);
        order.setDueToDelivery(now.plusMinutes(30));
        order.setPackedAt(now.plusMinutes(10));
        order.setDeliveredAt(now.plusMinutes(40));

        var address = new com.example.garbandgo.entities.Address();
        address.setId(1);
        order.setAddress(address);

        var deliveryUser = new com.example.garbandgo.entities.User();
        deliveryUser.setId(1);
        order.setDeliveredBy(deliveryUser);

        var packer = new com.example.garbandgo.entities.User();
        packer.setId(1);
        order.setPackedBy(packer);

        var restaurant = new com.example.garbandgo.entities.Restaurant();
        restaurant.setId(1);
        order.setRestaurant(restaurant);

        order.setUser(customer);

        var promocode = new com.example.garbandgo.entities.Promocode();
        promocode.setId(1);
        order.setPromocode(promocode);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (CartItem cartItem : shoppingCart.getItems()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(cartItem.getProduct());
            orderProduct.setQuantity(cartItem.getQuantity());
            orderProduct.setPrice(cartItem.getProduct().getPrice());
            orderProducts.add(orderProduct);
        }

        order.setItems(orderProducts);
        orderService.saveOrder(order);

        shoppingCart.clear();
        redirectAttributes.addFlashAttribute("successMessage", "Поръчката беше направена успешно!");
        return "redirect:/user/userPage";
    }
}
