package entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "Product")
@Table(name = "products")
public class Product implements Serializable {
    private static final long serialVersionUID = -1237456920859395092L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public Product setId(Integer id) {
        this.id = id;
        return this;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}