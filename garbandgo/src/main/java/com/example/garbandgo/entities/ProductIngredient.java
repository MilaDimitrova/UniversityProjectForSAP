package com.example.garbandgo.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "ProductIngredient")
@Table(name = "product_ingredients")
public class ProductIngredient implements Serializable {
    private static final long serialVersionUID = -2778234636115368708L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient")
    private Ingredient ingredient;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public ProductIngredient setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ProductIngredient setId(Integer id) {
        this.id = id;
        return this;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}