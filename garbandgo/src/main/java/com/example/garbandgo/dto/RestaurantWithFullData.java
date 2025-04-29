package com.example.garbandgo.dto;

import java.sql.Time;
import java.time.LocalTime;

public class RestaurantWithFullData {
        private final Integer id;
        private final String restaurant;
        private final String logo;
        private final Float reputation;
        private final Time opensAt;
        private final Time closesAt;
        private final String dayOfWeek;
        private final String address;
        private final String town;
        private final String country;
        private final String zipCode;

        public RestaurantWithFullData(Integer id,
                                      String restaurant,
                                      String logo,
                                      Float reputation,
                                      Time opensAt,
                                      Time closesAt,
                                      String dayOfWeek,
                                      String address,
                                      String town,
                                      String zipCode,
                                      String country
        ) {

                this.id = id;
                this.restaurant = restaurant;
                this.logo = logo;
                this.reputation = reputation;
                this.opensAt = opensAt;
                this.closesAt = closesAt;
                this.dayOfWeek = dayOfWeek;
                this.address = address;
                this.town = town;
                this.country  = country;
                this.zipCode = zipCode;
        }

        public Integer getId() {
                return id;
        }

        public String getRestaurant() {
                return restaurant;
        }

        public Float getReputation() {
                return reputation;
        }

        public Time getOpensAt() {
                return opensAt;
        }

        public Time getClosesAt() {
                return closesAt;
        }

        public String getDayOfWeek() {
                return dayOfWeek;
        }

        public String getAddress() {
                return address;
        }

        public String getTown() {
                return town;
        }

        public String getCountry() { return  country;}

        public String getLogo() {
                return logo;
        }

        public String getZipCode() {
                return zipCode;
        }
}
