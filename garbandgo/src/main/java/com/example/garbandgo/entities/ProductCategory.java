package com.example.garbandgo.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity(name = "ProductCategory")
@Table(name = "product_category")
public class ProductCategory implements Serializable {
    private static final long serialVersionUID = -4220771944707092700L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "category", nullable = false)
    private String name;

    @Lob
    @Column(name = "image", nullable = false)
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
