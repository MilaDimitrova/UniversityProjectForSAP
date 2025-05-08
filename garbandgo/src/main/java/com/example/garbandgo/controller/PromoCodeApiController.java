package com.example.garbandgo.controller;

import com.example.garbandgo.repositories.PromocodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/promocode")
@CrossOrigin(origins = "*")
public class PromoCodeApiController {

    @Autowired
    private PromocodeRepository promocodeRepository;

    @GetMapping("/validate")
    public Map<String, Object> validate(@RequestParam("code") String promocode) {
        Map<String, Object> response = new HashMap<>();
        response.put("valid", false);

        Instant now = Instant.now();
        promocodeRepository.findAll().stream()
                .filter(p -> p.getPromocode().equalsIgnoreCase(promocode.trim())
                        && now.isAfter(p.getValidFrom())
                        && now.isBefore(p.getValidTo()))
                .findFirst()
                .ifPresent(p -> {
                    response.put("valid", true); // 🔑 това липсваше!
                    response.put("discount", p.getDiscount());
                });

        return response;
    }
}
