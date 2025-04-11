package com.example.garbandgo.entities;

import jakarta.persistence.*;
import com.example.garbandgo.entities.Address;
import com.example.garbandgo.entities.User;
import java.io.Serializable;

@Entity(name = "Restaurant")
@Table(name = "restaurants", indexes = {
        @Index(name = "address", columnList = "address"),
        @Index(name = "manager", columnList = "manager")
})
public class Restaurant implements Serializable {
    private static final long serialVersionUID = 6365001661853965856L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String restaurant;

    private String logo;

    private Address address;

    private Double reputation;

    private User manager;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manager", nullable = false)
    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Column(name = "reputation")
    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address", nullable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Lob
    @Column(name = "logo", nullable = false)
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Column(name = "restaurant", nullable = false)
    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}