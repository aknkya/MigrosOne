package com.example.demo.model;

public class StoreInfo {
    private String name;
    private double lat;
    private double lng;

    private StoreInfo(Builder builder) {
        this.name = builder.name;
        this.lat = builder.lat;
        this.lng = builder.lng;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
    public static class Builder {
        private String name;
        private double lat;
        private double lng;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lat(double lat) {
            this.lat = lat;
            return this;
        }

        public Builder lng(double lng) {
            this.lng = lng;
            return this;
        }

        public StoreInfo build() {
            return new StoreInfo(this);
        }
    }
}
