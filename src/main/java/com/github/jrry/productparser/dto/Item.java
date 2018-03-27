package com.github.jrry.productparser.dto;

import java.math.BigDecimal;

public class Item {
    private String name;
    private BigDecimal minimalPrice;
    private String imageInBase64;

    public Item() {}

    private Item(Builder builder) {
        this.name = builder.name;
        this.minimalPrice = builder.minimalPrice;
        this.imageInBase64 = builder.imageInBase64;
    }

    public static class Builder {
        private String name;
        private BigDecimal minimalPrice;
        private String imageInBase64;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder minimalPrice(BigDecimal minimalPrice) {
            this.minimalPrice = minimalPrice;
            return this;
        }

        public Builder imageInBase64(String imageInBase64) {
            this.imageInBase64 = imageInBase64;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMinimalPrice() {
        return minimalPrice;
    }

    public void setMinimalPrice(BigDecimal minimalPrice) {
        this.minimalPrice = minimalPrice;
    }

    public String getImageInBase64() {
        return imageInBase64;
    }

    public void setImageInBase64(String imageInBase64) {
        this.imageInBase64 = imageInBase64;
    }
}
