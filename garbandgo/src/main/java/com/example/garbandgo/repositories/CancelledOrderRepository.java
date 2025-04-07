package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.CancelledOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancelledOrderRepository extends JpaRepository<CancelledOrder, Integer> {
}