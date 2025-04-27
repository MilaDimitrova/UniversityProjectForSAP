package com.example.garbandgo.service;

import com.example.garbandgo.entities.Role;
import com.example.garbandgo.entities.User;
import com.example.garbandgo.repositories.RoleRepository;
import com.example.garbandgo.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

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
        validateUserForRegistration(user);
        String email = user.getEmail().toLowerCase();
        Role role = assignRoleBasedOnEmailDomain(email);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("Успешна регистрация: {}", user.getEmail());
        return userRepository.save(user);
    }

    public void updateUsername(String email, String newUsername) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Потребителят не е намерен."));
        user.setUsername(newUsername);
        userRepository.save(user);
        logger.info("Обновен username за: {}", email);
    }

    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Потребителят не е намерен."));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        logger.info("Обновена парола за: {}", email);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public PasswordEncoder getPasswordEncoder() {
        return this.passwordEncoder;
    }

    private void validateUserForRegistration(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Имейлът не може да бъде празен!");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Паролата не може да бъде празна!");
        }
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Имейлът вече се използва!");
        }
    }

    private Role assignRoleBasedOnEmailDomain(String email) {
        if (email.endsWith("@admgag.bg") || email.endsWith("@admgag.com")) {
            return roleRepository.findByRole("ADMIN")
                    .orElseThrow(() -> new IllegalArgumentException("Ролята ADMIN не е намерена."));
        } else if (email.endsWith("@managag.bg") || email.endsWith("@managag.com")) {
            return roleRepository.findByRole("MANAGER")
                    .orElseThrow(() -> new IllegalArgumentException("Ролята MANAGER не е намерена."));
        } else if (email.endsWith("@courgag.bg") || email.endsWith("@courgag.com")) {
            return roleRepository.findByRole("COURIER")
                    .orElseThrow(() -> new IllegalArgumentException("Ролята COURIER не е намерена."));
        } else if (email.endsWith("@rogag.bg") || email.endsWith("@rogag.com")) {
            return roleRepository.findByRole("REST_OWNER")
                    .orElseThrow(() -> new IllegalArgumentException("Ролята REST_OWNER не е намерена."));
        } else {
            return roleRepository.findByRole("USER")
                    .orElseThrow(() -> new IllegalArgumentException("Ролята USER не е намерена."));
        }
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public void registerManager(String username, String email, String password, String phone) {
        Role role = roleRepository.findByRole("MANAGER")
                .orElseThrow(() -> new IllegalArgumentException("Ролята MANAGER не е намерена."));
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhone(phone);
        user.setRole(role);
        userRepository.save(user);
        logger.info("Добавен нов мениджър: {}", email);
    }

    public void registerCourier(String username, String email, String password, String phone) {
        Role role = roleRepository.findByRole("COURIER")
                .orElseThrow(() -> new IllegalArgumentException("Ролята COURIER не е намерена."));
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhone(phone);
        user.setRole(role);
        userRepository.save(user);
        logger.info("Добавен нов куриер: {}", email);
    }
}
