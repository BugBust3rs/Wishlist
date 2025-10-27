package com.example.wishlist.Controller;

import com.example.wishlist.Model.User;
import com.example.wishlist.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("")
@Controller
public class WishlistController {

    private final WishlistService service;

    public WishlistController(WishlistService service){
        this.service = service;
    }

    @GetMapping
    public String index() {
        return "wishlist";
    }

    @GetMapping("/createUser")
    public String showCreateUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";
    }
}
