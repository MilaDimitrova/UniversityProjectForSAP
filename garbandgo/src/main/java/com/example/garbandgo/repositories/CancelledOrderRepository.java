package com.example.garbandgo.repositories;

import entities.CancelledOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancelledOrderRepository extends JpaRepository<CancelledOrder, Integer> {
}