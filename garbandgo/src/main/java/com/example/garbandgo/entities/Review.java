package com.example.garbandgo.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

@Entity(name = "Review")
@Table(name = "reviews", indexes = {
        @Index(name = "order_id", columnList = "order_id")
})
public class Review implements Serializable {
    private static final long serialVersionUID = -5733929068960573848L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String reviewText;

    private Integer review;

    private Integer orderId;

    private Instant dateCreated;

    @Convert(disableConversion = true)
    @Column(name = "date_created", nullable = false)
    public Instant getDateCreated() {
        return dateCreated;
    }

    public Review setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    @Column(name = "order_id", nullable = false)
    public Integer getOrderId() {
        return orderId;
    }

    public Review setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    @Column(name = "review", nullable = false)
    public Integer getReview() {
        return review;
    }

    public Review setReview(Integer review) {
        this.review = review;
        return this;
    }

    @Lob
    @Column(name = "review_text", nullable = false)
    public String getReviewText() {
        return reviewText;
    }

    public Review setReviewText(String reviewText) {
        this.reviewText = reviewText;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Review setId(Integer id) {
        this.id = id;
        return this;
    }
}