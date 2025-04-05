package com.example.garbandgo.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "Restaurant")
@Table(name = "restaurants")
public class Restaurant implements Serializable {
    private static final long serialVersionUID = 398009848747615156L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public Restaurant setId(Integer id) {
        this.id = id;
        return this;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}