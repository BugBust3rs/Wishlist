package com.example.wishlist.Service;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;

import com.example.wishlist.Repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WishlistService {

    private final WishListRepository repository;
    private final WishListRepository wishListRepository;

    public WishlistService(WishListRepository repository, WishListRepository wishListRepository){
        this.repository = repository;
        this.wishListRepository = wishListRepository;
    }

    public List<Wish> getWishes(int id) {
        return repository.getWishes();
    }

    public User getUser(int id) {
        List<User> users = repository.getUsers();
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public void deleteWish(int id){
     wishListRepository.deleteWish(id);
    }
}
