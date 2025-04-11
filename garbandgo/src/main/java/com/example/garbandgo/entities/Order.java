package com.example.garbandgo.entities;

import com.example.garbandgo.entities.Restaurant;
import com.example.garbandgo.entities.User;
import com.example.garbandgo.entities.Address;
import com.example.garbandgo.entities.Promocode;
import com.example.garbandgo.entities.CancelledOrder;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

@Entity(name = "Order")
@Table(name = "orders", indexes = {
        @Index(name = "delivered_by", columnList = "delivered_by"),
        @Index(name = "address", columnList = "address"),
        @Index(name = "restaurant", columnList = "restaurant"),
        @Index(name = "cancelled_id", columnList = "cancelled_id"),
        @Index(name = "promocode", columnList = "promocode"),
        @Index(name = "user", columnList = "user"),
        @Index(name = "packed_by", columnList = "packed_by")
})
public class Order implements Serializable {
    private static final long serialVersionUID = 4075112109745358038L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Restaurant restaurant;

    private User user;

    private Address address;

    private Promocode promocode;

    private Double additionalDiscount;

    private Double totalPrice;

    private Instant orderedAt;

    private Instant dueToDelivery;

    private Instant packedAt;

    private User packedBy;

    private Instant deliveredAt;

    private User deliveredBy;

    private CancelledOrder cancelled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancelled_id")
    public CancelledOrder getCancelled() {
        return cancelled;
    }

    public void setCancelled(CancelledOrder cancelled) {
        this.cancelled = cancelled;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivered_by", nullable = false)
    public User getDeliveredBy() {
        return deliveredBy;
    }

    public void setDeliveredBy(User deliveredBy) {
        this.deliveredBy = deliveredBy;
    }

    @Column(name = "delivered_at")
    public Instant getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Instant deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "packed_by", nullable = false)
    public User getPackedBy() {
        return packedBy;
    }

    public void setPackedBy(User packedBy) {
        this.packedBy = packedBy;
    }

    @Column(name = "packed_at")
    public Instant getPackedAt() {
        return packedAt;
    }

    public void setPackedAt(Instant packedAt) {
        this.packedAt = packedAt;
    }

    @Column(name = "due_to_delivery", nullable = false)
    public Instant getDueToDelivery() {
        return dueToDelivery;
    }

    public void setDueToDelivery(Instant dueToDelivery) {
        this.dueToDelivery = dueToDelivery;
    }

    @Column(name = "ordered_at", nullable = false)
    public Instant getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(Instant orderedAt) {
        this.orderedAt = orderedAt;
    }

    @Column(name = "total_price", nullable = false)
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(name = "additional_discount", nullable = false)
    public Double getAdditionalDiscount() {
        return additionalDiscount;
    }

    public void setAdditionalDiscount(Double additionalDiscount) {
        this.additionalDiscount = additionalDiscount;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "promocode", nullable = false)
    public Promocode getPromocode() {
        return promocode;
    }

    public void setPromocode(Promocode promocode) {
        this.promocode = promocode;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address", nullable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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