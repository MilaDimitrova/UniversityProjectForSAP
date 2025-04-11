package com.example.garbandgo.entities;

import jakarta.persistence.*;
import com.example.garbandgo.entities.Order;
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

    private Order orderId;

    private Instant dateCreated;

    @Convert(disableConversion = true)
    @Column(name = "date_created", nullable = false)
    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "order_id", nullable = false)
    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    @Column(name = "review", nullable = false)
    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    @Lob
    @Column(name = "review_text", nullable = false)
    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}