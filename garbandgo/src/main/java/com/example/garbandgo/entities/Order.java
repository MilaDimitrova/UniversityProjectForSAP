package com.example.garbandgo.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "Order")
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = -1655611415167510914L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Promocode promocode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promocode")
    public Promocode getPromocode() {
        return promocode;
    }

    public Order setPromocode(Promocode promocode) {
        this.promocode = promocode;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Order setId(Integer id) {
        this.id = id;
        return this;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}