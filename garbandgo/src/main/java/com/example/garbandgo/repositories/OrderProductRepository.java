package com.example.garbandgo.repositories;


import com.example.garbandgo.entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
}