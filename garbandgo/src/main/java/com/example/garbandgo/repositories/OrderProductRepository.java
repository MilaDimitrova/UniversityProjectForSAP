package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    List<OrderProduct> findByOrderId(Integer orderId);
}