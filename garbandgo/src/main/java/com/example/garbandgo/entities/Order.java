package com.example.garbandgo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "Order")
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = -1655611415167510914L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promocode", referencedColumnName = "id")
    private Promocode promocode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivered_by", referencedColumnName = "id")
    private User deliveredBy;

    public Integer getId() {
        return id;
    }

    public Order setId(Integer id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Order setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Order setStatus(String status) {
        this.status = status;
        return this;
    }

    public Promocode getPromocode() {
        return promocode;
    }

    public Order setPromocode(Promocode promocode) {
        this.promocode = promocode;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }

    public User getDeliveredBy() {
        return deliveredBy;
    }

    public Order setDeliveredBy(User deliveredBy) {
        this.deliveredBy = deliveredBy;
        return this;
    }

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public Order setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
        return this;
    }

}
