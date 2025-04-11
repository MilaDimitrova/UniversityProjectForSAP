package com.example.garbandgo.entities;

import jakarta.persistence.*;
import com.example.garbandgo.entities.ProductCategory;
import com.example.garbandgo.entities.Restaurant;
import java.io.Serializable;

@Entity(name = "Product")
@Table(name = "products", indexes = {
        @Index(name = "restaurant", columnList = "restaurant"),
        @Index(name = "currency", columnList = "currency"),
        @Index(name = "category", columnList = "category")
})
public class Product implements Serializable {
    private static final long serialVersionUID = 8823829297324062695L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String product;

    private ProductCategory category;

    private String image;

    private String description;

    private Restaurant restaurant;

    private Double deliveryPrice;

    private Double price;

    private Integer currency;

    @Column(name = "currency", nullable = false)
    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    @Column(name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "delivery_price", nullable = false)
    public Double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant", nullable = false)
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Lob
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Lob
    @Column(name = "image", nullable = false)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category", nullable = false)
    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @Column(name = "product", nullable = false)
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}