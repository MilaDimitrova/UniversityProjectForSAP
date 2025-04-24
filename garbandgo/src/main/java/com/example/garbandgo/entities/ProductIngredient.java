package com.example.garbandgo.entities;

import  com.example.garbandgo.entities.Product;
import com.example.garbandgo.entities.Ingredient;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity(name = "ProductIngredient")
@Table(name = "product_ingredients", indexes = {
        @Index(name = "product", columnList = "product"),
        @Index(name = "ingredient", columnList = "ingredient")
})
public class ProductIngredient implements Serializable {
    private static final long serialVersionUID = -2421851507177211768L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Product product;

    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingredient", nullable = false)
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}