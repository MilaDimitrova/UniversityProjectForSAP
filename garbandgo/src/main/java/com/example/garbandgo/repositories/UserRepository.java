package com.example.garbandgo.repositories;


import com.example.garbandgo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}