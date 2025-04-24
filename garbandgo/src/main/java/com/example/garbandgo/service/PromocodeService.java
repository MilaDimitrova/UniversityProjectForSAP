package com.example.garbandgo.service;

import com.example.garbandgo.entities.Promocode;
import com.example.garbandgo.repositories.PromocodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocodeService {
    private final PromocodeRepository promocodeRepository;

    public PromocodeService(PromocodeRepository promocodeRepository) {
        this.promocodeRepository = promocodeRepository;
    }

    public List<Promocode> getPromocodeRepository() {
        return promocodeRepository.findAll();
    }

    public void savePromocode(Promocode promocode) {
        promocodeRepository.save(promocode);
    }
}
