package com.example.garbandgo.repositories;


import com.example.garbandgo.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}