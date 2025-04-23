package com.example.garbandgo.service;

import com.example.garbandgo.entities.Role;
import com.example.garbandgo.entities.User;
import com.example.garbandgo.repositories.RoleRepository;
import com.example.garbandgo.repositories.UserRepository;
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

    public User registerUser(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Имейлът не може да бъде празен!");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Паролата не може да бъде празна!");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Имейлът вече се използва!");
        }

       
        String email = user.getEmail().toLowerCase();
        Role role;

        if (email.endsWith("@admgag.bg") || email.endsWith("@admgag.com")) {
            role = roleRepository.findByRole("ADMIN")
                    .orElseThrow(() -> new IllegalArgumentException("Ролята ADMIN не е намерена."));
        } else if (email.endsWith("@managag.bg") || email.endsWith("@managag.com")) {
            role = roleRepository.findByRole("MANAGER")
                    .orElseThrow(() -> new IllegalArgumentException("Ролята MANAGER не е намерена."));
        } else if (email.endsWith("@courgag.bg") || email.endsWith("@courgag.com")) {
            role = roleRepository.findByRole("COURIER")
                    .orElseThrow(() -> new IllegalArgumentException("Ролята COURIER не е намерена."));
        } else if (email.endsWith("@rogag.bg") || email.endsWith("@rogag.com")) {
            role = roleRepository.findByRole("REST_OWNER")
                    .orElseThrow(() -> new IllegalArgumentException("Ролята REST_OWNER не е намерена."));
        } else {
            role = roleRepository.findByRole("USER")
                    .orElseThrow(() -> new IllegalArgumentException("Ролята USER не е намерена."));
        }

        user.setRole(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("Успешна регистрация: {}", user.getEmail());
        return userRepository.save(user);
    }
}
