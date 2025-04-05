package com.example.garbandgo.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "Promocode")
@Table(name = "promocodes", indexes = {
        @Index(name = "restaurant", columnList = "restaurant"),
        @Index(name = "promocode", columnList = "promocode", unique = true)
})
public class Promocode implements Serializable {
    private static final long serialVersionUID = -3534821349989230563L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String promocode;

    private String description;

    private Restaurant restaurant;

    private Instant validFrom;

    private Instant validTo;

    private Integer discount;

    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "promocode")
    public Set<Order> getOrders() {
        return orders;
    }

    public Promocode setOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    @Column(name = "discount", nullable = false)
    public Integer getDiscount() {
        return discount;
    }

    public Promocode setDiscount(Integer discount) {
        this.discount = discount;
        return this;
    }

    @Convert(disableConversion = true)
    @Column(name = "valid_to", nullable = false)
    public Instant getValidTo() {
        return validTo;
    }

    public Promocode setValidTo(Instant validTo) {
        this.validTo = validTo;
        return this;
    }

    @Convert(disableConversion = true)
    @Column(name = "valid_from", nullable = false)
    public Instant getValidFrom() {
        return validFrom;
    }

    public Promocode setValidFrom(Instant validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant", nullable = false)
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Promocode setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    @Lob
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public Promocode setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "promocode", nullable = false, length = 60)
    public String getPromocode() {
        return promocode;
    }

    public Promocode setPromocode(String promocode) {
        this.promocode = promocode;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Promocode setId(Integer id) {
        this.id = id;
        return this;
    }
}