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
    private Integer id;

    private String reviewText;

    private Integer review;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order orderId;  // Много към едно връзка с Order (не променяме Order ентити)

    private Instant dateCreated;

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

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