package com.example.wishlist.Model;


import java.util.List;

public class Wishlist {
    private List<Wish> wishlist;
    private String name;
    private int userId;


    public Wishlist(List<Wish> wishList, String name, int userId){
        this.wishlist = wishList;
        this.name = name;
        this.userId = userId;
    }

    public Wishlist(){

    }

    public List<Wish> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<Wish> wishlist) {
        this.wishlist = wishlist;
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
