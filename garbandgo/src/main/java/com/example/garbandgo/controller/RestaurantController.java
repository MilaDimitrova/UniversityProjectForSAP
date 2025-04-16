package com.example.garbandgo.controller;

import com.example.garbandgo.dto.RestaurantWithFullData;
import com.example.garbandgo.entities.Restaurant;
import com.example.garbandgo.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // GET /restaurants - Index: Retrieve all restaurants
    @GetMapping("/index")
    public String index(Model model) {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        model.addAttribute("restaurants", restaurants);
        return "restaurants/index";
    }

    // GET /restaurants/{id} - Show: Retrieve a single restaurant by its ID
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantWithFullData> show(@PathVariable Integer id) {
        RestaurantWithFullData restaurant = restaurantService.getRestaurantById(id);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /restaurants - Add: Create a new restaurant
    @PostMapping
    public Restaurant add(@RequestBody Restaurant newRestaurant) {
        return restaurantService.saveRestaurant(newRestaurant);
    }

    // PUT /restaurants/{id} - Update: Update an existing restaurant
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable Integer id, @RequestBody Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantService.updateRestaurant(id, updatedRestaurant);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /restaurants/{id} - Delete: Remove a restaurant by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = restaurantService.deleteRestaurant(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
