package com.example.redo;

public class Listing {
    private String id;
    private String name;
    private String organization;
    private Double price;
    private String description;
    private String sellerId;

    public Listing() {
    }

    public Listing(String name, String organization, Double price, String description, String sellerId) {
        this.name = name;
        this.organization = organization;
        this.price = price;
        this.description = description;
        this.sellerId = sellerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
}
