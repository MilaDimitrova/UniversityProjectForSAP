package entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "Town")
@Table(name = "towns")
public class Town implements Serializable {
    private static final long serialVersionUID = 4469630107692399245L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country")
    public Country getCountry() {
        return country;
    }

    public Town setCountry(Country country) {
        this.country = country;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Town setId(Integer id) {
        this.id = id;
        return this;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}