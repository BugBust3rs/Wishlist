package com.example.wishlist.Controller;

import com.example.wishlist.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("")
@Controller
public class WishlistController {

    private final WishlistService service;

    public WishlistController(WishlistService service){
        this.service = service;
    }
}
