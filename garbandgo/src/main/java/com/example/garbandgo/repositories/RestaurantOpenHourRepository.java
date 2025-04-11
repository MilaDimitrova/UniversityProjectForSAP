package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.RestaurantOpenHour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantOpenHourRepository extends JpaRepository<RestaurantOpenHour, Integer> {
}