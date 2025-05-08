package com.example.garbandgo.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PromocodeDTO implements Serializable {
    private Integer id;
    private String promocode;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime validFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime validTo;

    private Integer discount;
    private Integer restaurantID;
    private String restaurant;

    public PromocodeDTO() {}

    public PromocodeDTO(Integer id,
                        String promocode,
                        String description,
                        Timestamp validFrom,
                        Timestamp validTo,
                        Integer discount,
                        Integer restaurantID,
                        String restaurant) {
        this.id = id;
        this.promocode = promocode;
        this.description = description;
        this.restaurantID = restaurantID;
        this.restaurant = restaurant;
        this.validFrom = validFrom.toLocalDateTime();
        this.validTo = validTo.toLocalDateTime();
        this.discount = discount;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromocode() {
        return promocode;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(Integer restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}