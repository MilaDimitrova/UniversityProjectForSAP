package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.CancelledOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface CancelledOrderRepository extends JpaRepository<CancelledOrder, Integer> {

    List<CancelledOrder> findByCanceledAtBetween(Instant start, Instant end);
}
