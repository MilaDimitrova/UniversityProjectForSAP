package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Order;
import com.example.garbandgo.entities.OrderProduct;
import com.example.garbandgo.entities.Product;
import com.example.garbandgo.repositories.ProductRepository;
import com.example.garbandgo.service.OrderProductService;
import com.example.garbandgo.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order_products")
public class OrderProductController {

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/index")
    public String index(@RequestParam("orderId") Integer orderId,
                        @RequestParam(name = "productId", required = false) Integer productId,
                        Model model) {

        List<OrderProduct> orderProducts = orderProductService.getByOrderId(orderId);
        List<Product> products = productRepository.findAll();
        Product selectedProduct = null;

        if (productId != null) {
            selectedProduct = productRepository.findById(productId).orElse(null);
        }

        Order order = orderService.getOrderById(orderId).orElse(null);
        if (order == null) {
            return "redirect:/orders/index"; // fallback ако поръчката липсва
        }

        model.addAttribute("order", order);
        model.addAttribute("orderProducts", orderProducts);
        model.addAttribute("products", products);
        model.addAttribute("selectedProduct", selectedProduct);
        model.addAttribute("orderId", orderId);

        return "Orders/order_product_list";
    }

    @GetMapping
    public String redirectToIndex() {
        return "redirect:/orders/index";
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderProduct> show(@PathVariable Integer id) {
        OrderProduct orderProduct = orderProductService.getById(id);
        return orderProduct != null ?
                ResponseEntity.ok(orderProduct) :
                ResponseEntity.notFound().build();
    }


    @PostMapping("/add")
    public String addProductToOrder(@RequestParam("orderId") Integer orderId,
                                    @RequestParam("productId") Integer productId,
                                    @RequestParam("quantity") Integer quantity,
                                    @RequestParam("price") Double price) {

        Product product = productRepository.findById(productId).orElse(null);
        Order order = orderService.getOrderById(orderId).orElse(null);

        if (product == null || order == null) {
            return "redirect:/order_products/index?orderId=" + orderId;
        }

        OrderProduct op = new OrderProduct();
        op.setOrder(order);
        op.setProduct(product);
        op.setQuantity(quantity);
        op.setPrice(price);

        orderProductService.save(op);
        return "redirect:/order_products/index?orderId=" + orderId;
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderProduct> update(@PathVariable Integer id, @RequestBody OrderProduct updatedOrderProduct) {
        OrderProduct result = orderProductService.update(id, updatedOrderProduct);
        return result != null ?
                ResponseEntity.ok(result) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = orderProductService.delete(id);
        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
