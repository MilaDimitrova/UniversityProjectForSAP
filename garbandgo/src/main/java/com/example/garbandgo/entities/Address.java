package com.example.garbandgo.entities;

import jakarta.persistence.*;
import com.example.garbandgo.entities.User;
import com.example.garbandgo.entities.Town;

import java.io.Serializable;

@Entity(name = "Address")
@Table(name = "addresses", indexes = {
        @Index(name = "town", columnList = "town"),
        @Index(name = "user", columnList = "user")
})
public class Address implements Serializable {
    private static final long serialVersionUID = 4761916890768860541L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String address;

    private Town town;

    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "town", nullable = false)
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Column(name = "address", nullable = false, length = 150)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}