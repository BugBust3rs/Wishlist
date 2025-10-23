package com.example.wishlist.Controller;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("")
@Controller
public class WishlistController {

    private final WishlistService service;

    public WishlistController(WishlistService service){
        this.service = service;
    }

    @GetMapping("/wishes")
    public String getWishes(HttpSession session, Model model) {

        int userId = session.getAttribute("user").getId();
        User user = service.getUser( userId);
        model.addAttribute("user", user);
        List<Wish> wishes = service.getWishes(userId);
        model.addAttribute("wishes", wishes);
        return "wishlist";
    }

    @PostMapping("/{wishId}/wishes")
    public String deleteWish(@PathVariable int wishId){
        service.deleteWish(wishId);
        return "redirect:/{id}/wishes";
    }


}
