package com.example.garbandgo.service;

import com.example.garbandgo.entities.User;
import com.example.garbandgo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Trying to log in with email: " + email); // ЛОГ

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("No user found with email: " + email); // ЛОГ
                    return new UsernameNotFoundException("User not found with email: " + email);
                });

        String roleName = user.getRole().getRole();
        System.out.println("Found user: " + user.getEmail() + ", role: " + roleName); // ЛОГ

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(roleName)
                .build();
    }

}
