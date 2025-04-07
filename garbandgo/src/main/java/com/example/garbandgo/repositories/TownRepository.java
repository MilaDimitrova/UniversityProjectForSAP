package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, Integer> {
}