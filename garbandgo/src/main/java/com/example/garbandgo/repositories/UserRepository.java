package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    ;

   @Query("SELECT u FROM User u WHERE u.role.role = :role")
   List<User> findAllByRoleName(@Param("role") String role);
}