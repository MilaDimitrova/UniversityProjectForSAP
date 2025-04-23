package com.example.garbandgo.entities;

import jakarta.persistence.*;
import com.example.garbandgo.entities.Order;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "CancelledOrder")
@Table(name = "cancelled_orders", indexes = {
        @Index(name = "order_id", columnList = "order_id")
})
public class CancelledOrder implements Serializable {
    private static final long serialVersionUID = 5297276254395683337L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Order order;

    private Instant canceledAt;

    private String reason;

    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cancelled")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Lob
    @Column(name = "reason", nullable = false)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Convert(disableConversion = true)
    @Column(name = "canceled_at", nullable = false)
    public Instant getCanceledAt() {
        return canceledAt;
    }

    public void setCanceledAt(Instant canceledAt) {
        this.canceledAt = canceledAt;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}