package com.example.garbandgo.service;

import com.example.garbandgo.entities.CancelledOrder;
import com.example.garbandgo.repositories.CancelledOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancelledOrderService {

    @Autowired
    private CancelledOrderRepository cancelledOrderRepository;

    // Retrieve all cancelled orders
    public List<CancelledOrder> getAllCancelledOrders() {
        return cancelledOrderRepository.findAll();
    }

    // Retrieve a cancelled order by ID
    public CancelledOrder getCancelledOrderById(Integer id) {
        Optional<CancelledOrder> cancelledOrder = cancelledOrderRepository.findById(id);
        return cancelledOrder.orElse(null);
    }

    // Save a new cancelled order
    public CancelledOrder saveCancelledOrder(CancelledOrder cancelledOrder) {
        return cancelledOrderRepository.save(cancelledOrder);
    }

    // Update an existing cancelled order
    public CancelledOrder updateCancelledOrder(Integer id, CancelledOrder updatedOrder) {
        Optional<CancelledOrder> existing = cancelledOrderRepository.findById(id);
        if (existing.isPresent()) {
            updatedOrder.setId(id);
            return cancelledOrderRepository.save(updatedOrder);
        }
        return null;
    }

    // Delete a cancelled order by ID
    public boolean deleteCancelledOrder(Integer id) {
        if (cancelledOrderRepository.existsById(id)) {
            cancelledOrderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
