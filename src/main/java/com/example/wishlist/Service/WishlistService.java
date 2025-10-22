package com.example.wishlist.Service;


import com.example.wishlist.Repository.WishListRepository;
import org.springframework.stereotype.Service;


@Service
public class WishlistService {

    private final WishListRepository repository;

    public WishlistService(WishListRepository repository){
        this.repository = repository;
    }


}
