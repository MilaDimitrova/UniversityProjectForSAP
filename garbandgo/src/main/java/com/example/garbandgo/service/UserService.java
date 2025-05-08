package com.example.garbandgo.service;

import com.example.garbandgo.entities.Role;
import com.example.garbandgo.entities.User;
import com.example.garbandgo.repositories.RoleRepository;
import com.example.garbandgo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(User user, String roleName) {
        validateUserForRegistration(user);

        Role role = roleRepository.findByRole(roleName.toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException("Ролята " + roleName + " не е намерена."));

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        logger.info("Регистриран потребител с роля {}: {}", roleName, user.getEmail());
        return userRepository.save(user);
    }

    private void validateUserForRegistration(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Имейлът не може да бъде празен!");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Паролата не може да бъде празна!");
        }
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new IllegalArgumentException("Потребителското име не може да бъде празно!");
        }
    }


    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}