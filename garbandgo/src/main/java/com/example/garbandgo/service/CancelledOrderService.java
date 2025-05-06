package com.example.garbandgo.service;

import com.example.garbandgo.entities.CancelledOrder;
import com.example.garbandgo.repositories.CancelledOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CancelledOrderService {

    @Autowired
    private CancelledOrderRepository cancelledOrderRepository;

    public List<CancelledOrder> getAllCancelledOrders() {
        return cancelledOrderRepository.findAll();
    }

    public CancelledOrder getCancelledOrderById(Integer id) {
        Optional<CancelledOrder> cancelledOrder = cancelledOrderRepository.findById(id);
        return cancelledOrder.orElse(null);
    }

    public CancelledOrder saveCancelledOrder(CancelledOrder cancelledOrder) {
        return cancelledOrderRepository.save(cancelledOrder);
    }

    public CancelledOrder updateCancelledOrder(Integer id, CancelledOrder updatedOrder) {
        Optional<CancelledOrder> existing = cancelledOrderRepository.findById(id);
        if (existing.isPresent()) {
            updatedOrder.setId(id);
            return cancelledOrderRepository.save(updatedOrder);
        }
        return null;
    }

    public boolean deleteCancelledOrder(Integer id) {
        if (cancelledOrderRepository.existsById(id)) {
            cancelledOrderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CancelledOrder> getCancelledOrdersByDateRange(LocalDateTime from, LocalDateTime to) {
        return cancelledOrderRepository.findByCanceledAtBetween(
                from.atZone(java.time.ZoneId.systemDefault()).toInstant(),
                to.atZone(java.time.ZoneId.systemDefault()).toInstant()
        );
    }
}
