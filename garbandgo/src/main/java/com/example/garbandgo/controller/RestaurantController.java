package com.example.garbandgo.controller;

import com.example.garbandgo.dto.RestaurantWithFullData;
import com.example.garbandgo.entities.Address;
import com.example.garbandgo.entities.Country;
import com.example.garbandgo.entities.Restaurant;
import com.example.garbandgo.entities.User;
import com.example.garbandgo.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // GET /restaurants - Index: Retrieve all restaurants
    @GetMapping("/index")
    public String index(Model model) {
        List<RestaurantWithFullData> restaurants = restaurantService.getAllRestaurants();
        model.addAttribute("restaurants", restaurants);
        return "restaurants/index";
    }

    // GET /restaurants/{id} - Show: Retrieve a single restaurant by its ID
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        List<RestaurantWithFullData> restaurant = restaurantService.getRestaurantById(id);
        if (restaurant != null) {
            model.addAttribute("restaurant", restaurant);
            return "restaurants/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        }
    }

    @GetMapping("/add")
    public String getAddRestaurantView() {
        return "restaurants/add"; // Thymeleaf will look for 'add.html' in '/resources/templates/restaurants/'
    }


    // POST /restaurants - Add: Create a new restaurant
    @PostMapping("/add")
    public String add ( @RequestParam String restaurant,
                        @RequestParam String logo,
                        @RequestParam String address,
                        @RequestParam String town,
                        @RequestParam String country,
                        @RequestParam String zipCode,
                        @RequestParam Double reputation,
                        @RequestParam(required = false) String opensMon,
                        @RequestParam(required = false) String closesMon,
                        @RequestParam(required = false) String opensTue,
                        @RequestParam(required = false) String closesTue,
                        @RequestParam(required = false) String opensWed,
                        @RequestParam(required = false) String closesWed,
                        @RequestParam(required = false) String opensThu,
                        @RequestParam(required = false) String closesThu,
                        @RequestParam(required = false) String opensFri,
                        @RequestParam(required = false) String closesFri,
                        @RequestParam(required = false) String opensSat,
                        @RequestParam(required = false) String closesSat,
                        @RequestParam(required = false) String opensSun,
                        @RequestParam(required = false) String closesSun,
                        Authentication authentication,
                        RedirectAttributes redirectAttributes) {
        //User manager = (User) authentication.getPrincipal();

        try {
            Time tOpensMon = (opensMon == null || opensMon.trim().isEmpty()) ? null : Time.valueOf(opensMon);
            Time tClosesMon = (closesMon == null || closesMon.trim().isEmpty()) ? null : Time.valueOf(closesMon);
            Time tOpensTue = (opensTue == null || opensTue.trim().isEmpty()) ? null : Time.valueOf(opensTue);
            Time tClosesTue = (closesTue == null || closesTue.trim().isEmpty()) ? null : Time.valueOf(closesTue);
            Time tOpensWed = (opensWed == null || opensWed.trim().isEmpty()) ? null : Time.valueOf(opensWed);
            Time tClosesWed = (closesWed == null || closesWed.trim().isEmpty()) ? null : Time.valueOf(closesWed);
            Time tOpensThu = (opensThu == null || opensThu.trim().isEmpty()) ? null : Time.valueOf(opensThu);
            Time tClosesThu = (closesThu == null || closesThu.trim().isEmpty()) ? null : Time.valueOf(closesThu);
            Time tOpensFri = (opensFri == null || opensFri.trim().isEmpty()) ? null : Time.valueOf(opensFri);
            Time tClosesFri = (closesFri == null || closesFri.trim().isEmpty()) ? null : Time.valueOf(closesFri);
            Time tOpensSat = (opensSat == null || opensSat.trim().isEmpty()) ? null : Time.valueOf(opensSat);
            Time tClosesSat = (closesSat == null || closesSat.trim().isEmpty()) ? null : Time.valueOf(closesSat);
            Time tOpensSun = (opensSun == null || opensSun.trim().isEmpty()) ? null : Time.valueOf(opensSun);
            Time tClosesSun = (closesSun == null || closesSun.trim().isEmpty()) ? null : Time.valueOf(closesSun);

            restaurantService.addRestaurant(
                    //restaurant, logo, address, town, Integer.parseInt(country), zipCode, reputation, manager.getId(),
                    restaurant, logo, address, town, Integer.parseInt(country), zipCode, reputation, 1,
                    tOpensMon, tClosesMon,
                    tOpensTue, tClosesTue,
                    tOpensWed, tClosesWed,
                    tOpensThu, tClosesThu,
                    tOpensFri, tClosesFri,
                    tOpensSat, tClosesSat,
                    tOpensSun, tClosesSun
            );
            redirectAttributes.addFlashAttribute("message", "Restaurant added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding restaurant: " + e.getMessage());
        }
        return "redirect:/restaurants/index";

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
