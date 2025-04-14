package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query(value="SELECT r.id, r.restaurant, r.logo, r.reputation, roh.opens_at, roh.closes_at, roh.day_of_week, adr.address, t.town FROM restaurants r\n" +
            "JOIN restaurant_open_hours roh ON roh.restaurant = r.id\n" +
            "JOIN addresses adr ON r.address = adr.id\n" +
            "JOIN towns t ON adr.town = t.id;", nativeQuery = true)
    List<Restaurant> findAllRestaurantsWithFullData();

    @Query(value="SELECT r.id, r.restaurant, r.logo, r.reputation, roh.opens_at, roh.closes_at, roh.day_of_week, adr.address, t.town FROM restaurants r\n" +
            "JOIN restaurant_open_hours roh ON roh.restaurant = r.id\n" +
            "JOIN addresses adr ON r.address = adr.id\n" +
            "JOIN towns t ON adr.town = t.id" +
            "WHERE r.id = :id;", nativeQuery = true)
    Restaurant findRestaurantWithFullData(@Param("id") Integer id);

}