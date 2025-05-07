package com.example.garbandgo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = -1655611415167510914L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant", referencedColumnName = "id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "promocode", referencedColumnName = "id")
    private Promocode promocode;

    @Column(name = "additional_discount")
    private Double additionalDiscount;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "ordered_at")
    private LocalDateTime orderDate;

    @Column(name = "due_to_delivery")
    private LocalDateTime dueToDelivery;

    @Column(name = "packed_at")
    private LocalDateTime packedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packed_by", referencedColumnName = "id")
    private User packedBy;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivered_by", referencedColumnName = "id")
    private User deliveredBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancelled_id", referencedColumnName = "id")
    private CancelledOrder cancelledOrder;

    @Column(name = "cancelled")
    private byte[] cancelled;


    public Integer getId() {
        return id;
    }

    public Order setId(Integer id) {
        this.id = id;
        return this;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Order setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Order setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Promocode getPromocode() {
        return promocode;
    }

    public Order setPromocode(Promocode promocode) {
        this.promocode = promocode;
        return this;
    }

    public Double getAdditionalDiscount() {
        return additionalDiscount;
    }

    public Order setAdditionalDiscount(Double additionalDiscount) {
        this.additionalDiscount = additionalDiscount;
        return this;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Order setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public LocalDateTime getDueToDelivery() {
        return dueToDelivery;
    }

    public Order setDueToDelivery(LocalDateTime dueToDelivery) {
        this.dueToDelivery = dueToDelivery;
        return this;
    }

    public LocalDateTime getPackedAt() {
        return packedAt;
    }

    public Order setPackedAt(LocalDateTime packedAt) {
        this.packedAt = packedAt;
        return this;
    }

    public User getPackedBy() {
        return packedBy;
    }

    public Order setPackedBy(User packedBy) {
        this.packedBy = packedBy;
        return this;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public Order setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
        return this;
    }

    public User getDeliveredBy() {
        return deliveredBy;
    }

    public Order setDeliveredBy(User deliveredBy) {
        this.deliveredBy = deliveredBy;
        return this;
    }

    public CancelledOrder getCancelledOrder() {
        return cancelledOrder;
    }

    public Order setCancelledOrder(CancelledOrder cancelledOrder) {
        this.cancelledOrder = cancelledOrder;
        return this;
    }

    public byte[] getCancelled() {
        return cancelled;
    }

    public Order setCancelled(byte[] cancelled) {
        this.cancelled = cancelled;
        return this;
    }

    public boolean isCancelled() {
        return cancelled != null;
    }
}
