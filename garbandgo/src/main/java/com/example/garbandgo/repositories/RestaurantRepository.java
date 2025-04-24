package com.example.garbandgo.repositories;

import com.example.garbandgo.dto.RestaurantWithFullData;
import com.example.garbandgo.entities.Restaurant;
import com.example.garbandgo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Optional<Restaurant> findByManager(User manager);
    @Query(value="SELECT id, restaurant, logo, reputation, opensAt, closesAt, dayOfWeek, address, town, zipCode, country " +
            "FROM (" +
            "SELECT r.id, r.restaurant, r.logo, r.reputation, " +
            "roh.opens_at as opensAt, roh.closes_at as closesAt, roh.day_of_week as dayOfWeek,  " +
            "adr.address, t.town, t.zip_code as zipCode, c.country," +
            "ROW_NUMBER() OVER (PARTITION BY r.restaurant ORDER BY r.id) AS row_num " +
            "FROM restaurants r " +
            "JOIN restaurant_open_hours roh ON roh.restaurant = r.id " +
            "JOIN addresses adr ON r.address = adr.id " +
            "JOIN towns t ON adr.town = t.id " +
            "JOIN countries c ON t.country = c.id" +
            ") AS subquery " +
            "WHERE row_num = 1;;", nativeQuery = true)
    List<RestaurantWithFullData> findAllRestaurantsWithFullData();

    @Query(value="SELECT r.id, r.restaurant, r.logo, r.reputation, roh.opens_at as opensAt, roh.closes_at as closesAt, " +
            "roh.day_of_week as dayOfWeek, adr.address, t.town, t.zip_code as zipCode, c.country " +
            "FROM restaurants r " +
            "JOIN restaurant_open_hours roh ON roh.restaurant = r.id " +
            "JOIN addresses adr ON r.address = adr.id " +
            "JOIN towns t ON adr.town = t.id " +
            "JOIN countries c ON t.country = c.id " +
            "WHERE r.id = :id;", nativeQuery = true)
    List<RestaurantWithFullData> findRestaurantWithFullData(@Param("id") Integer id);



}