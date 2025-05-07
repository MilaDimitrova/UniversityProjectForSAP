package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.Order;
import com.example.garbandgo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByDeliveredByIsNull();
    List<Order> findByDeliveredBy(User user);
    Optional<Order> findById(Integer id);
    List<Order> findByOrderDateAfter(LocalDateTime dateTime);
}
