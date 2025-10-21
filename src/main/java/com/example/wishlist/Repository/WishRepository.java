package com.example.wishlist.Repository;


import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepository {

    public User getUser(String name){
        return new User();
    }

    public Wish addWish(Wish wish){
        return new Wish();
    }

}
