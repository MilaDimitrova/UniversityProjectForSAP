package com.example.garbandgo.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "Country")
@Table(name = "countries", indexes = {
        @Index(name = "currency", columnList = "currency")
})
public class Country implements Serializable {
    private static final long serialVersionUID = 2584223320276821414L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String country;

    private Currency currency;

    private Set<Town> towns = new LinkedHashSet<>();

    @OneToMany(mappedBy = "country")
    public Set<Town> getTowns() {
        return towns;
    }

    public Country setTowns(Set<Town> towns) {
        this.towns = towns;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency", nullable = false)
    public Currency getCurrency() {
        return currency;
    }

    public Country setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    @Column(name = "country", nullable = false, length = 100)
    public String getCountry() {
        return country;
    }

    public Country setCountry(String country) {
        this.country = country;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Country setId(Integer id) {
        this.id = id;
        return this;
    }
}