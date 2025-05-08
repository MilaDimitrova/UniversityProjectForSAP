package com.example.garbandgo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = -1655611415167510914L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "promocode")
    private Promocode promocode;

    private Double additionalDiscount;
    private Double totalPrice;
    private LocalDateTime orderDate;
    private LocalDateTime dueToDelivery;
    private LocalDateTime packedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packed_by")
    private User packedBy;

    private LocalDateTime deliveredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivered_by")
    private User deliveredBy;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private CancelledOrder cancelledOrder;

    private byte[] cancelled;

    @Column(name = "status")
    private String status;

    @Column(name = "ordered_at", nullable = false)
    private LocalDateTime orderedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> items = new ArrayList<>();


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
        return cancelled != null && cancelled.length > 0 && cancelled[0] == 1;
    }

    public String getStatus() {
        return status;
    }

    public Order setStatus(String status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public Order setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
        return this;
    }

    public List<OrderProduct> getItems() {
        return items;
    }

    public void setItems(List<OrderProduct> items) {
        this.items = items;
    }
}
