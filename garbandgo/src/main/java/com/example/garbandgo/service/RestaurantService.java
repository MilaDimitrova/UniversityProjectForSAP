package com.example.garbandgo.service;

import com.example.garbandgo.dto.RestaurantWithFullData;
import com.example.garbandgo.entities.*;
import com.example.garbandgo.repositories.RestaurantRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service

public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public void addRestaurant(String restaurant, String logo, String address,
                              String town, Integer country, String zipCode,
                              Double reputation, Integer manager,
                              Time opensMon, Time closesMon,
                              Time opensTue, Time closesTue,
                              Time opensWed, Time closesWed,
                              Time opensThu, Time closesThu,
                              Time opensFri, Time closesFri,
                              Time opensSat, Time closesSat,
                              Time opensSun, Time closesSun) {
        String sql = "CALL add_restaurant_with_open_hours(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                restaurant, logo, address, town, country, zipCode, reputation, manager,
                opensMon, closesMon,
                opensTue, closesTue,
                opensWed, closesWed,
                opensThu, closesThu,
                opensFri, closesFri,
                opensSat, closesSat,
                opensSun, closesSun
        );

    }


    // Update an existing restaurant
    public void updateRestaurant(int restaurantId, String restaurant, String logo, double reputation,
                                 String address, String town, int country, String zipCode, int manager,
                                 Time opensMon, Time closesMon,
                                 Time opensTue, Time closesTue,
                                 Time opensWed, Time closesWed,
                                 Time opensThu, Time closesThu,
                                 Time opensFri, Time closesFri,
                                 Time opensSat, Time closesSat,
                                 Time opensSun, Time closesSun) {

        String sql = "CALL update_restaurant_with_open_hours(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                restaurantId, restaurant, logo, reputation, address, town, country, zipCode, manager,
                opensMon, closesMon,
                opensTue, closesTue,
                opensWed, closesWed,
                opensThu, closesThu,
                opensFri, closesFri,
                opensSat, closesSat,
                opensSun, closesSun
        );
    }


    // Delete a restaurant by its ID
    public void deleteRestaurant(int restaurantId) {
        // Retrieve the address ID for the restaurant
        String getAddressQuery = "SELECT address FROM restaurants WHERE id = ?";
        Integer addressId = jdbcTemplate.queryForObject(getAddressQuery, Integer.class, restaurantId);

        if (addressId != null) {
            String deleteOpenHoursQuery = "DELETE FROM restaurant_open_hours WHERE restaurant = ?";
            jdbcTemplate.update(deleteOpenHoursQuery, restaurantId);

            String deleteRestaurantQuery = "DELETE FROM restaurants WHERE id = ?";
            jdbcTemplate.update(deleteRestaurantQuery, restaurantId);

            String deleteAddressQuery = "DELETE FROM addresses WHERE id = ?";
            jdbcTemplate.update(deleteAddressQuery, addressId);
        }
    }
}
