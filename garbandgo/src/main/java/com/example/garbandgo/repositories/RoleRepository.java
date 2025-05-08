package com.example.garbandgo.repositories;
import com.example.garbandgo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findById(Integer id);
    Optional<Role> findByRole(String role);
}
