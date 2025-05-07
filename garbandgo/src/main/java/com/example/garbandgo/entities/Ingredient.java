package com.example.garbandgo.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "Ingredient")
@Table(name = "ingredients")
public class Ingredient implements Serializable {
    private static final long serialVersionUID = -4137292273993352592L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String ingredient;

    private Boolean alergen = false;

    private Set<ProductIngredient> productIngredients = new LinkedHashSet<>();

    @OneToMany(mappedBy = "ingredient")
    public Set<ProductIngredient> getProductIngredients() {
        return productIngredients;
    }

    public Ingredient setProductIngredients(Set<ProductIngredient> productIngredients) {
        this.productIngredients = productIngredients;
        return this;
    }

    @Column(name = "alergen", nullable = false)
    public Boolean getAlergen() {
        return alergen;
    }

    public void setAlergen(Boolean alergen) {
        this.alergen = alergen;
    }

    @Column(name = "ingredient", nullable = false)
    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}