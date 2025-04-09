package com.example.garbandgo.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "Currency")
@Table(name = "currencies")
public class Currency implements Serializable {
    private static final long serialVersionUID = 292217000731363047L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public Currency setId(Integer id) {
        this.id = id;
        return this;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}