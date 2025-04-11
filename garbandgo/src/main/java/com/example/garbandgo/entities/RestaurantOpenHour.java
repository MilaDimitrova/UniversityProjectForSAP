package com.example.garbandgo.entities;

import jakarta.persistence.*;
import com.example.garbandgo.entities.Restaurant;
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

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Convert(disableConversion = true)
    @Column(name = "closes_at")
    public LocalTime getClosesAt() {
        return closesAt;
    }

    public void setClosesAt(LocalTime closesAt) {
        this.closesAt = closesAt;
    }

    @Convert(disableConversion = true)
    @Column(name = "opens_at")
    public LocalTime getOpensAt() {
        return opensAt;
    }

    public void setOpensAt(LocalTime opensAt) {
        this.opensAt = opensAt;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant", nullable = false)
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}