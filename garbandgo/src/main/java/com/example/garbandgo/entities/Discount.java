package com.example.garbandgo.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

@Entity(name = "Discount")
@Table(name = "discounts", indexes = {
        @Index(name = "restautant", columnList = "restautant"),
        @Index(name = "added_by", columnList = "added_by"),
        @Index(name = "discounted_category", columnList = "discounted_category"),
        @Index(name = "discounted_product", columnList = "discounted_product")
})
public class Discount implements Serializable {
    private static final long serialVersionUID = -4238599227980320079L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Double discount;

    private Instant validFrom;

    private Instant validTo;

    private Restaurant restaurant;

    private ProductCategory discountedCategory;

    private Product discountedProduct;

    private User addedBy;

    private Instant addedAt;

    @Convert(disableConversion = true)
    @Column(name = "added_at", nullable = false)
    public Instant getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "added_by", nullable = false)
    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discounted_product")
    public Product getDiscountedProduct() {
        return discountedProduct;
    }

    public void setDiscountedProduct(Product discountedProduct) {
        this.discountedProduct = discountedProduct;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discounted_category")
    public ProductCategory getDiscountedCategory() {
        return discountedCategory;
    }

    public void setDiscountedCategory(ProductCategory discountedCategory) {
        this.discountedCategory = discountedCategory;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant", nullable = false)
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Convert(disableConversion = true)
    @Column(name = "valid_to", nullable = false)
    public Instant getValidTo() {
        return validTo;
    }

    public void setValidTo(Instant validTo) {
        this.validTo = validTo;
    }

    @Convert(disableConversion = true)
    @Column(name = "valid_from", nullable = false)
    public Instant getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Instant validFrom) {
        this.validFrom = validFrom;
    }

    @Column(name = "discount", nullable = false)
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}