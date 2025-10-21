package com.example.wishlist.Repository;


import com.example.wishlist.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepository {

    public User getUser(String name){
        return new User();
    }
}
