package com.example.garbandgo.dto;

import java.time.LocalTime;

public class RestaurantWithFullData {
        private Integer id;
        private String restaurant;
        private String logo;
        private Double reputation;
        private LocalTime opensAt;
        private LocalTime closesAt;
        private String dayOfWeek;
        private String address;
        private String town;


        public Integer getId() {
                return id;
        }

        public String getRestaurant() {
                return restaurant;
        }

        public Double getReputation() {
                return reputation;
        }

        public LocalTime getOpensAt() {
                return opensAt;
        }

        public LocalTime getClosesAt() {
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
}
