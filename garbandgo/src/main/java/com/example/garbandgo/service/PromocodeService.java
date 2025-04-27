package com.example.garbandgo.service;

import com.example.garbandgo.dto.PromocodeDTO;
import com.example.garbandgo.entities.Promocode;
import com.example.garbandgo.entities.Restaurant;
import com.example.garbandgo.repositories.PromocodeRepository;
import com.example.garbandgo.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class PromocodeService {
    private final PromocodeRepository promocodeRepository;
    private final RestaurantRepository restaurantRepository;

    public PromocodeService(PromocodeRepository promocodeRepository, RestaurantRepository restaurantRepository) {
        this.promocodeRepository = promocodeRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<PromocodeDTO> getAllPromocodes() {
        return promocodeRepository.findAllPromocodes();
    }

    public Optional<PromocodeDTO> getPromocodeById(Integer id) {
        Optional<PromocodeDTO> promocode = promocodeRepository.findPromocodeById(id);
        return promocode;
    }

    public void saveDTO(PromocodeDTO promocodeDTO, Integer restaurantId, Optional<Integer> id ) {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);
        Optional<Promocode> promocodeOpt = promocodeRepository.findById(id.get().intValue());
        Promocode promocode;
        if (restaurantOpt.isPresent()) {
            if (promocodeOpt.isPresent()){
                promocode = promocodeOpt.get();
            }else {
                promocode = new Promocode();
            }

            promocode.setPromocode(promocodeDTO.getPromocode());
            promocode.setDescription(promocodeDTO.getDescription());
            promocode.setDiscount(promocodeDTO.getDiscount());
            promocode.setValidFrom(promocodeDTO.getValidFrom().atZone(ZoneId.systemDefault()).toInstant());
            promocode.setValidTo(promocodeDTO.getValidTo().atZone(ZoneId.systemDefault()).toInstant());
            promocode.setRestaurant(restaurantOpt.get().getId());
            promocodeRepository.save(promocode);
        } else {
            throw new RuntimeException("Restaurant not found with ID: " + restaurantId);
        }
    }


    public Promocode save(Promocode promocode) {
        return promocodeRepository.save(promocode);
    }

    public void deleteById(Integer id) {
        promocodeRepository.deleteById(id);
    }
}