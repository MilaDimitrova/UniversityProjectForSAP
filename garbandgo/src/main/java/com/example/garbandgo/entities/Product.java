package com.example.garbandgo.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity(name = "Product")
@Table(name = "products", indexes = {
        @Index(name = "idx_restaurant", columnList = "restaurant"),
        @Index(name = "idx_currency", columnList = "currency"),
        @Index(name = "idx_category", columnList = "category")
})
public class Product implements Serializable {
    private static final long serialVersionUID = 8823829297324062695L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    // Полето "name" се мапва с колоната "product" в базата данни
    @Column(name = "product", nullable = false)
    private String name;

    // Мапиране на категорията; използваме името "category", тъй като таблицата съдържа колоната "category"
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category", nullable = false)
    private ProductCategory category;

    // Полето imageUrl се мапва към колоната "image"
    @Lob
    @Column(name = "image", nullable = false)
    private String imageUrl;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    // Мапиране на ресторанта; използваме името "restaurant", съобразно таблицата
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;

    @Column(name = "delivery_price", nullable = false)
    private Double deliveryPrice;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "currency", nullable = false)
    private Integer currency;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // За името на продукта
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    // За URL на изображението
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }
}
