package com.example.wishlist.Model;


import java.util.List;

public class Wishlist {
    private List<Wish> wishes;
    private String name;
    private int userId;
    private int wishlistId;


    public Wishlist(List<Wish> wishes, String name, int userId){
        this.wishes = wishes;
        this.name = name;
        this.userId = userId;
    }

    public Wishlist(){

    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
