package com.example.garbandgo.entities;

import jakarta.persistence.*;
import com.example.garbandgo.entities.Currency;

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

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency", nullable = false)
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Column(name = "country", nullable = false, length = 100)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}