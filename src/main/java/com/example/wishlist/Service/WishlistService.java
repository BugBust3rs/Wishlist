package com.example.wishlist.Service;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    private final WishListRepository wishListRepository;


    public WishlistService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public List<User> getUsers(){
        return wishListRepository.getUsers();
    }

    public List<Wish> getWishes() {
    }
}
