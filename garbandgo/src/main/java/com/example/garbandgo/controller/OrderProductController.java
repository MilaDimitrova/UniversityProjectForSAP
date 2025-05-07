package com.example.garbandgo.controller;

import com.example.garbandgo.entities.OrderProduct;
import com.example.garbandgo.entities.Product;
import com.example.garbandgo.entities.Order;
import com.example.garbandgo.service.OrderProductService;
import com.example.garbandgo.repositories.ProductRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
@RequestMapping("/order_products")
public class OrderProductController {

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private ProductRepository productRepository;

    // GET /order_products/index - показва всички orderProducts + форма за добавяне
    @GetMapping("/index")
    public String index(@RequestParam(required = false) Integer productId, Model model) {
        List<OrderProduct> orderProducts = orderProductService.getAll();
        List<Product> products = productRepository.findAll();
        Product selectedProduct = null;

        if (productId != null) {
            selectedProduct = productRepository.findById(productId).orElse(null);
        }

        model.addAttribute("orderProducts", orderProducts);
        model.addAttribute("products", products);
        model.addAttribute("selectedProduct", selectedProduct);

        return "Orders/order_product_list";
    }

    // Redirect към /index
    @GetMapping
    public String redirectToIndex() {
        return "redirect:/order_products/index";
    }

    // Връща JSON за един OrderProduct по ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderProduct> show(@PathVariable Integer id) {
        OrderProduct orderProduct = orderProductService.getById(id);
        if (orderProduct != null) {
            return ResponseEntity.ok(orderProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Добавяне на нов OrderProduct
    @PostMapping("/add")
    public String addProductToOrder(@RequestParam("productId") Integer productId,
                                    @RequestParam("quantity") Integer quantity,
                                    @RequestParam("price") Double price) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return "redirect:/order_products/index";

        OrderProduct op = new OrderProduct();
        op.setProduct(product);
        op.setQuantity(quantity);
        op.setPrice(price);

        Order dummyOrder = new Order(); // временно
        dummyOrder.setId(1);
        op.setOrder(dummyOrder);

        orderProductService.save(op);
        return "redirect:/order_products/index";
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderProduct> update(@PathVariable Integer id, @RequestBody OrderProduct updatedOrderProduct) {
        OrderProduct result = orderProductService.update(id, updatedOrderProduct);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = orderProductService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
