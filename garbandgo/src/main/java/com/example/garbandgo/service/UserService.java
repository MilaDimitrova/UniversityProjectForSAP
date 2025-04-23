package com.example.garbandgo.service;

import com.example.garbandgo.entities.Role;
import com.example.garbandgo.entities.User;
import com.example.garbandgo.repositories.RoleRepository;
import com.example.garbandgo.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    // Добавяме RoleRepository към конструктора
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder passwordEncoder) {
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

        // Проверка дали role-а е зададен правилно
        if (user.getRole() == null || user.getRole().getId() == null) {
            throw new IllegalArgumentException("Ролята не е зададена правилно!");
        }

        // Вземаме управлявания Role обект от базата по id
        int roleId = user.getRole().getId();
        Role managedRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Ролята с id " + roleId + " не е намерена."));
        user.setRole(managedRole);

        // Кодираме паролата
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("Успешна регистрация: {}", user.getEmail());
        return userRepository.save(user);
    }
}
