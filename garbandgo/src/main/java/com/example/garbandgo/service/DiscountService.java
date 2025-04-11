package com.example.garbandgo.service;

import com.example.garbandgo.repositories.DiscountRepository;
import com.example.garbandgo.entities.Discount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> getDiscountRepository() {
        return discountRepository.findAll();
    }

    public void saveDicsount(Discount discount) {
        discountRepository.save(discount);
    }
}
