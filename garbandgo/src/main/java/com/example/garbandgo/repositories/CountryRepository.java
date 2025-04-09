package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}