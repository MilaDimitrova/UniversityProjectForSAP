package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.Promocode;
import com.example.garbandgo.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PromocodeRepository extends JpaRepository<Promocode, Integer> {

}