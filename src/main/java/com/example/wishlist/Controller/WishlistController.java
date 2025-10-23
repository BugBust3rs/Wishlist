package com.example.wishlist.Controller;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("")
@Controller
public class WishlistController {

    private final WishlistService service;

    public WishlistController(WishlistService service){
        this.service = service;
    }

    @GetMapping("/{id}/wishes")
    public String getWishes(@PathVariable int id, Model model) {
        List<Wish> wishes = service.getWishes();
        model.addAttribute("wishes", wishes);
        User user = service.getUser( id);
        model.addAttribute("user", user);
        return "wishlist";
    }


}
