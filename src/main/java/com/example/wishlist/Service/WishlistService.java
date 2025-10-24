package com.example.wishlist.Service;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;

import com.example.wishlist.Repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class WishlistService {

    private final WishListRepository repository;

    public WishlistService(WishListRepository repository){
        this.repository = repository;
    }

    public List<Wish> getWishesFromUser(int userId) {
        ArrayList<Wish> wishList = new ArrayList<>();
        ArrayList<Wish> userWishList = new ArrayList<>();
        for(Wish wish: wishList){
            if(wish.getUserId() == userId){
            userWishList.add(wish);
            }
        }
        return userWishList;
    }



    public void deleteWish(int wishId) {

    }
}
