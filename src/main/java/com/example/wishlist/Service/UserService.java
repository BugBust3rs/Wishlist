package com.example.wishlist.Service;

import com.example.wishlist.Model.User;
import com.example.wishlist.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
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

    public User login(String email, String pw) {
        List<User> users = repository.getUsers();
        for (User user : users){
            if (user.getEmail().equals(email) && user.getPassword().equals(pw)){
                return user;
            }
        }
        return null;
    }

    public boolean doesUserExists(String email){
        List<User> users = repository.getUsers();

        for(User user : users){
         if(user.getEmail().equals(email)){
             return  true;
         }
        }
        return false;
    }

    public void createUser(User user){
        repository.createUser(user);
    }
}
