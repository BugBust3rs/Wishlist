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

    private final WishRepository wishRepository;
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishRepository wishRepository, WishlistRepository wishlistRepository) {
        this.wishRepository = wishRepository;
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wish> getWishesFromUser(int wishlist_id) {
        List<Wish> wishList = wishRepository.getWishes();
        List<Wish> userWishList = new ArrayList<>();
        for (Wish wish : wishList) {
            if (wish.getWishlistId() == wishlist_id) {
                userWishList.add(wish);
            }
        }
        return userWishList;
    }


    public void deleteWish(int wishId) {
        wishRepository.deleteWish(wishId);
    }

    public void saveWish(Wish wish) {
        wishRepository.updateWish(wish);
    }

    public Wish getWishFromWishId(int wishId) {
        List<Wish> wishes = wishRepository.getWishes();
        for (Wish wish : wishes){
            if (wish.getId() == wishId){
                return wish;
            }
        }
        return null;
    }

    public Wishlist getOneWishlistFromUser(int userId) {
        List<Wishlist> wishlists = wishlistRepository.getWishlists();

        for (Wishlist wishlist : wishlists) {
            if (wishlist.getUserId() == userId) {
                return wishlist;
            }
        }
        return null;
    }

    public List<Wishlist> getAllWishlistsFromUser(int user_id) {
        List<Wishlist> wishlists = wishlistRepository.getWishlists();
        List<Wishlist> userWishlists = new ArrayList<>();
        for (Wishlist wishlist : wishlists) {
            if (wishlist.getUserId() == user_id) {
                userWishlists.add(wishlist);
            }
        }
        return userWishlists;
    }


    public Wishlist getWishlist(int wishlistId) {
        List<Wishlist> wishlists = wishlistRepository.getWishlists();
        for(Wishlist wishlist : wishlists) {
            if (wishlist.getWishlistId() == wishlistId) {
                return wishlist;
            }
        }
        return null;
    }

    public void saveWishlist(Wishlist wishlist) {
        wishlistRepository.addWishlist(wishlist);
    }

    public Wish updateReservation(boolean isReserved) {
        return null;
    }
}
