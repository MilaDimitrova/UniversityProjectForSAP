package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.Order;
import com.example.garbandgo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByDeliveredByIsNull();
    List<Order> findByDeliveredBy(User user);
}
