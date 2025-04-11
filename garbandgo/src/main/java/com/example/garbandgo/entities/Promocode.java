package com.example.garbandgo.entities;

import jakarta.persistence.*;
import com.example.garbandgo.entities.Restaurant;
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

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Column(name = "discount", nullable = false)
    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Convert(disableConversion = true)
    @Column(name = "valid_to", nullable = false)
    public Instant getValidTo() {
        return validTo;
    }

    public void setValidTo(Instant validTo) {
        this.validTo = validTo;
    }

    @Convert(disableConversion = true)
    @Column(name = "valid_from", nullable = false)
    public Instant getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Instant validFrom) {
        this.validFrom = validFrom;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant", nullable = false)
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Lob
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "promocode", nullable = false, length = 60)
    public String getPromocode() {
        return promocode;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}