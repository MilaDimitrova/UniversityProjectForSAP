package com.example.garbandgo.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "OrderProduct")
@Table(name = "order_products", indexes = {
        @Index(name = "product", columnList = "product"),
        @Index(name = "order_id", columnList = "order_id")
})
public class OrderProduct implements Serializable {
    private static final long serialVersionUID = 5132411891024340384L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Order order;

    private Product product;

    private Integer qunatity;

    private Double price;

    @Column(name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }

    public OrderProduct setPrice(Double price) {
        this.price = price;
        return this;
    }

    @Column(name = "qunatity", nullable = false)
    public Integer getQunatity() {
        return qunatity;
    }

    public OrderProduct setQunatity(Integer qunatity) {
        this.qunatity = qunatity;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product", nullable = false)
    public Product getProduct() {
        return product;
    }

    public OrderProduct setProduct(Product product) {
        this.product = product;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    public Order getOrder() {
        return order;
    }

    public OrderProduct setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public OrderProduct setId(Integer id) {
        this.id = id;
        return this;
    }
}