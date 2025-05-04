package com.example.garbandgo.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity(name = "User")
@Table(name = "users", indexes = {
        @Index(name = "role", columnList = "role")
})
public class User implements Serializable {
    private static final long serialVersionUID = -3752540106382239509L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "email", nullable = false, length = 70)
    private String email;

    @Column(name = "phone", nullable = false, length = 10)
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @Column(name = "password", nullable = false)
    private String password;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}