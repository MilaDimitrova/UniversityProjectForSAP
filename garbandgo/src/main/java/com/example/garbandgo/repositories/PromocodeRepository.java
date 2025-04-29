package com.example.garbandgo.repositories;

import com.example.garbandgo.dto.PromocodeDTO;
import com.example.garbandgo.dto.RestaurantWithFullData;
import com.example.garbandgo.entities.Promocode;
import com.example.garbandgo.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PromocodeRepository extends JpaRepository<Promocode, Integer> {
    @Query(value="SELECT p.id, p.promocode, p.description, p.valid_from as validFrom, p.valid_to as ValidTo, p.discount," +
            " p.restaurant as restaurantID, r.restaurant " +
            "FROM promocodes p " +
            "JOIN restaurants r " +
            "ON p.restaurant = r.id;", nativeQuery = true)
    List<PromocodeDTO> findAllPromocodes();

    @Query(value="SELECT p.id, p.promocode, p.description, p.valid_from as validFrom, p.valid_to as ValidTo, p.discount," +
            " p.restaurant as restaurantID, r.restaurant " +
            "FROM promocodes p " +
            "JOIN restaurants r " +
            "ON p.restaurant = r.id WHERE p.id = :id;", nativeQuery = true)
    Optional<PromocodeDTO> findPromocodeById(@Param("id") Integer id);
}