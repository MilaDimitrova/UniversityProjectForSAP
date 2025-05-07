package com.example.garbandgo.controller;

import com.example.garbandgo.entities.CancelledOrder;
import com.example.garbandgo.service.CancelledOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cancelled_orders")
public class CancelledOrderController {

    @Autowired
    private CancelledOrderService cancelledOrderService;

    // GET /cancelled_orders/index - Show all cancelled orders
    @GetMapping("/index")
    public String index(Model model) {
        List<CancelledOrder> cancelledOrders = cancelledOrderService.getAllCancelledOrders();
        model.addAttribute("cancelledOrders", cancelledOrders);
        return "Orders/cancelled_orders";
    }

    @GetMapping
    public String redirectToIndex() {
        return "redirect:/cancelled_orders/index";
    }

    // GET /cancelled_orders/{id} - Retrieve a specific cancelled order by ID
    @GetMapping("/{id}")
    public ResponseEntity<CancelledOrder> show(@PathVariable Integer id) {
        CancelledOrder order = cancelledOrderService.getCancelledOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /cancelled_orders - Create a new cancelled order
    @PostMapping
    public CancelledOrder add(@RequestBody CancelledOrder newOrder) {
        return cancelledOrderService.saveCancelledOrder(newOrder);
    }

    // PUT /cancelled_orders/{id} - Update existing cancelled order
    @PutMapping("/{id}")
    public ResponseEntity<CancelledOrder> update(@PathVariable Integer id, @RequestBody CancelledOrder updatedOrder) {
        CancelledOrder order = cancelledOrderService.updateCancelledOrder(id, updatedOrder);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /cancelled_orders/{id} - Delete a cancelled order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = cancelledOrderService.deleteCancelledOrder(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
