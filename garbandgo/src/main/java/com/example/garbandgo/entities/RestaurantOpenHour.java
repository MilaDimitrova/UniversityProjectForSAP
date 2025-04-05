package com.example.garbandgo.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalTime;

@Entity(name = "RestaurantOpenHour")
@Table(name = "restaurant_open_hours", indexes = {
        @Index(name = "restaurant", columnList = "restaurant")
})
public class RestaurantOpenHour implements Serializable {
    private static final long serialVersionUID = -253297018542259421L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Restaurant restaurant;

    private LocalTime opensAt;

    private LocalTime closesAt;

    private String dayOfWeek;

    @Lob
    @Column(name = "day_of_week", nullable = false)
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public RestaurantOpenHour setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    @Convert(disableConversion = true)
    @Column(name = "closes_at")
    public LocalTime getClosesAt() {
        return closesAt;
    }

    public RestaurantOpenHour setClosesAt(LocalTime closesAt) {
        this.closesAt = closesAt;
        return this;
    }

    @Convert(disableConversion = true)
    @Column(name = "opens_at")
    public LocalTime getOpensAt() {
        return opensAt;
    }

    public RestaurantOpenHour setOpensAt(LocalTime opensAt) {
        this.opensAt = opensAt;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant", nullable = false)
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public RestaurantOpenHour setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public RestaurantOpenHour setId(Integer id) {
        this.id = id;
        return this;
    }
}