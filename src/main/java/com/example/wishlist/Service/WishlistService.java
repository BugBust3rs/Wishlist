package com.example.wishlist.Service;

import com.example.wishlist.Model.Wish;

import com.example.wishlist.Repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class WishlistService {

    private final WishRepository repository;

    public WishlistService(WishRepository repository) {
        this.repository = repository;
    }

    public List<Wish> getWishesFromUser(int userId) {
        List<Wish> wishList = repository.getWishes();
        List<Wish> userWishList = new ArrayList<>();
        for (Wish wish : wishList) {
            if (wish.getUserId() == userId) {
                userWishList.add(wish);
            }
        }
        return userWishList;
    }


    public void deleteWish(int wishId) {
        repository.deleteWish(wishId);
    }

    public void saveWish(Wish wish) {
        repository.addWish(wish);
    }

    public Wish getWishFromWishId(int wishId) {
        List<Wish> wishes = repository.getWishes();
        for (Wish wish : wishes){
            if (wish.getId() == wishId){
                return wish;
            }
        }
        return null;
    }
}
