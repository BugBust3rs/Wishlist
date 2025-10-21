package com.example.wishlist.Repository;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository {
    public User getUser(String name){
        return new User();
    }

    public Wish addWish(Wish wish){
        String sql = "INSERT INTO WishList ()";
    }
}
