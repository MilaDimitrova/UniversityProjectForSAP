package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, Integer> {
}