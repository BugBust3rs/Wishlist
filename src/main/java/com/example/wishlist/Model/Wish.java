package com.example.wishlist.Model;

public class Wish {
    private int id;
    private int userId;
    private String name;
    private String description;
    private double price;
    private String link;
    private  Boolean isReverved;

    public Wish(int id, int userId, String name, String description, double price, String link, Boolean isReverved){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.link = link;
        this.isReverved = isReverved;
    }

    public Wish(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getReverved() {
        return isReverved;
    }

    public void setReverved(Boolean reverved) {
        isReverved = reverved;
    }
}
