package com.example.garbandgo.entities;

import jakarta.persistence.*;
import com.example.garbandgo.entities.Country;
import java.io.Serializable;

@Entity(name = "Town")
@Table(name = "towns", indexes = {
        @Index(name = "country", columnList = "country"),
        @Index(name = "town", columnList = "town, zip_code", unique = true)
})
public class Town implements Serializable {
    private static final long serialVersionUID = -6729549169723947930L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String town;

    private Country country;

    private String zipCode;

    @Column(name = "zip_code", nullable = false, length = 4)
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country", nullable = false)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Column(name = "town", nullable = false, length = 200)
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}