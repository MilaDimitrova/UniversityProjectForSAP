package com.example.garbandgo.repositories;


import com.example.garbandgo.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}