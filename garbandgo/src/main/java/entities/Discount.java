package entities;

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

    private Restaurant restautant;

    private ProductCategory discountedCategory;

    private Product discountedProduct;

    private User addedBy;

    private Instant addedAt;

    @Convert(disableConversion = true)
    @Column(name = "added_at", nullable = false)
    public Instant getAddedAt() {
        return addedAt;
    }

    public Discount setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "added_by", nullable = false)
    public User getAddedBy() {
        return addedBy;
    }

    public Discount setAddedBy(User addedBy) {
        this.addedBy = addedBy;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discounted_product")
    public Product getDiscountedProduct() {
        return discountedProduct;
    }

    public Discount setDiscountedProduct(Product discountedProduct) {
        this.discountedProduct = discountedProduct;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discounted_category")
    public ProductCategory getDiscountedCategory() {
        return discountedCategory;
    }

    public Discount setDiscountedCategory(ProductCategory discountedCategory) {
        this.discountedCategory = discountedCategory;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restautant", nullable = false)
    public Restaurant getRestautant() {
        return restautant;
    }

    public Discount setRestautant(Restaurant restautant) {
        this.restautant = restautant;
        return this;
    }

    @Convert(disableConversion = true)
    @Column(name = "valid_to", nullable = false)
    public Instant getValidTo() {
        return validTo;
    }

    public Discount setValidTo(Instant validTo) {
        this.validTo = validTo;
        return this;
    }

    @Convert(disableConversion = true)
    @Column(name = "valid_from", nullable = false)
    public Instant getValidFrom() {
        return validFrom;
    }

    public Discount setValidFrom(Instant validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    @Column(name = "discount", nullable = false)
    public Double getDiscount() {
        return discount;
    }

    public Discount setDiscount(Double discount) {
        this.discount = discount;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Discount setId(Integer id) {
        this.id = id;
        return this;
    }
}