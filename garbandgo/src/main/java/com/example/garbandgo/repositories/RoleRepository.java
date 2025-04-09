package com.example.garbandgo.repositories;


import com.example.garbandgo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}