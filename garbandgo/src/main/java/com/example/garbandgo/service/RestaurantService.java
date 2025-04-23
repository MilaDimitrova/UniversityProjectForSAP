package com.example.garbandgo.service;

import com.example.garbandgo.dto.RestaurantWithFullData;
import com.example.garbandgo.entities.Restaurant;
import com.example.garbandgo.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Retrieve all restaurants
    public List<RestaurantWithFullData> getAllRestaurants() {
        return restaurantRepository.findAllRestaurantsWithFullData();
    }

    // Retrieve a specific restaurant by its ID
    public List<RestaurantWithFullData> getRestaurantById(Integer id) {
        List<RestaurantWithFullData> restaurants = restaurantRepository.findRestaurantWithFullData(id);
        return restaurants != null ? restaurants : Collections.emptyList(); // Avoid returning null
    }

    // Save a new restaurant
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    // Update an existing restaurant
    public Restaurant updateRestaurant(Integer id, Restaurant updatedRestaurant) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(id);
        if (existingRestaurant.isPresent()) {
            updatedRestaurant.setId(id);
            return restaurantRepository.save(updatedRestaurant);
        }
        return null;
    }

    // Delete a restaurant by its ID
    public boolean deleteRestaurant(Integer id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
