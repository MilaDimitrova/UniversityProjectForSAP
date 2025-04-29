package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.Product;
import com.example.garbandgo.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByRestaurant(Restaurant restaurant);
    List<Product> findByRestaurantId(Integer restaurantId);


}
