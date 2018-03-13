package com.github.jrry.productparser.dto;

import java.math.BigDecimal;

public class Item {
    private String name;
    private BigDecimal minimalPrice;
    private String imageBase64;

    public Item() {};

    public Item(String name, BigDecimal minimalPrice, String imageBase64) {
        this.name = name;
        this.minimalPrice = minimalPrice;
        this.imageBase64 = imageBase64;
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

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
