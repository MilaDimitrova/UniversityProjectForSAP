package com.example.garbandgo.service;

import com.example.garbandgo.entities.CancelledOrder;
import com.example.garbandgo.entities.Order;
import com.example.garbandgo.entities.User;
import com.example.garbandgo.repositories.CancelledOrderRepository;
import com.example.garbandgo.repositories.OrderRepository;
import com.example.garbandgo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CancelledOrderRepository cancelledOrderRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersCreatedAfter(LocalDateTime dateTime) {
        return orderRepository.findByOrderDateAfter(dateTime);
    }

    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("PENDING");
        }
        if (order.getCancelled() == null) {
            order.setCancelled(new byte[]{0});
        }
        if (order.getOrderedAt() == null) {
            order.setOrderedAt(LocalDateTime.now());
        }
        return orderRepository.save(order);
    }

    public Order updateOrder(Integer id, Order updatedOrder) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setPromocode(updatedOrder.getPromocode());
            return orderRepository.save(order);
        }
        return null;
    }

    public boolean deleteOrder(Integer id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean cancelOrder(Integer id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            if (order.getCancelled() == null || order.getCancelled()[0] == 0) {
                order.setCancelled(new byte[]{1});
                order.setStatus("CANCELLED");

                CancelledOrder cancelled = new CancelledOrder();
                cancelled.setReason("Canceled by courier");
                cancelled.setCanceledAt(Instant.now());
                cancelled.setOrder(order);

                cancelledOrderRepository.save(cancelled);
                order.setCancelledOrder(cancelled);

                orderRepository.save(order);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean markAsDelivered(Integer id) {
        System.out.println("➡️ markAsDelivered е извикан за поръчка ID: " + id);

        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            System.out.println("🔍 Намерена поръчка със статус: " + order.getStatus());

            boolean isNotCancelled = order.getCancelled() == null || order.getCancelled()[0] == 0;
            boolean isPending = "PENDING".equalsIgnoreCase(order.getStatus());

            if (isNotCancelled && isPending) {
                order.setDeliveredAt(LocalDateTime.now());
                order.setStatus("DELIVERED");

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String username = authentication.getName();
                System.out.println("👤 Логнат потребител: " + username);

                Optional<User> optionalUser = userRepository.findByUsername(username);
                if (optionalUser.isEmpty()) {
                    System.out.println("❌ Липсва потребител в базата с username: " + username);
                    return false;
                }

                User currentUser = optionalUser.get();
                order.setDeliveredBy(currentUser);

                try {
                    orderRepository.save(order);
                    System.out.println("✅ Поръчка ID " + id + " е маркирана като ДОСТАВЕНА от " + currentUser.getUsername());
                    return true;
                } catch (Exception e) {
                    System.out.println("❌ SQL грешка при записване на поръчка ID: " + id);
                    e.printStackTrace();
                    return false;
                }
            } else {
                System.out.println("⚠️ Поръчка ID " + id + " не може да бъде доставена. Текущ статус: " + order.getStatus());
            }
        } else {
            System.out.println("❌ Няма поръчка с ID: " + id);
        }

        return false;
    }

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findAllByUser(user);
    }
}
