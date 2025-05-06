package com.example.garbandgo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "cancelled_orders", indexes = {
        @Index(name = "order_id", columnList = "order_id")
})
public class CancelledOrder implements Serializable {
    private static final long serialVersionUID = 5297276254395683337L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "canceled_at", nullable = false)
    private Instant canceledAt;

    @Column(name = "reason", nullable = false)
    private String reason;


    public Integer getId() {
        return id;
    }

    public CancelledOrder setId(Integer id) {
        this.id = id;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public CancelledOrder setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Instant getCanceledAt() {
        return canceledAt;
    }

    public CancelledOrder setCanceledAt(Instant canceledAt) {
        this.canceledAt = canceledAt;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public CancelledOrder setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
