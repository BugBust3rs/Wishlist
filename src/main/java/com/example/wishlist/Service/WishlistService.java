package com.example.wishlist.Service;

import com.example.wishlist.Model.Wish;

import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Repository.WishRepository;
import com.example.wishlist.Repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class WishlistService {

    private final WishRepository repository;
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishRepository repository, WishlistRepository wishlistRepository) {
        this.repository = repository;
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wish> getWishesFromUser(int wishlist_id) {
        List<Wish> wishList = repository.getWishes();
        List<Wish> userWishList = new ArrayList<>();
        for (Wish wish : wishList) {
            if (wish.getWishlistId() == wishlist_id) {
                userWishList.add(wish);
            }
        }
        return userWishList;
    }


    public void deleteWish(int wishId) {
        repository.deleteWish(wishId);
    }

    public void saveWish(Wish wish) {
        repository.updateWish(wish);
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

    public Wishlist getOneWishlistFromUser(int userId) {
        List<Wishlist> wishlists = wishlistRepository.getWishlists();

        for(Wishlist wishlist : wishlists){
            if(wishlist.getUserId() == userId){
              return  wishlist;
            }
        }
        return null;
    }

    public List<Wishlist> getAllWishlistsFromUser(int wishlist_id) {
        List<Wishlist> wishlists = wishlistRepository.getWishlists();
        List<Wishlist> userWishlists = new ArrayList<>();
        for(Wishlist wishlist : wishlists){
            if(wishlist.getUserId() == wishlist_id ){
                userWishlists.add(wishlist);
            }
        }
        return userWishlists;
    }


}
