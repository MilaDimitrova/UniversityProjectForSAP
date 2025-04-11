package com.example.garbandgo.service;

import com.example.garbandgo.entities.RestaurantOpenHour;
import com.example.garbandgo.repositories.RestaurantOpenHourRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantOpenHourService {
    private final RestaurantOpenHourRepository restaurantOpenHourRepository;

    public RestaurantOpenHourService(RestaurantOpenHourRepository restaurantOpenHourRepository) {
        this.restaurantOpenHourRepository = restaurantOpenHourRepository;
    }

    public List<RestaurantOpenHour> getRestaurantOpenHour() {
        return restaurantOpenHourRepository.findAll();
    }

    public void saveRestaurantOpenHour(RestaurantOpenHour restaurantOpenHour) {
        restaurantOpenHourRepository.save(restaurantOpenHour);
    }
}
