package com.example.wishlist.Controller;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Service.UserService;
import com.example.wishlist.Service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("")
@Controller
public class WishlistController {

    private final WishlistService wishlistService;
    private final UserService userService;

    public WishlistController(WishlistService wishlistService, UserService userService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
    }

    @PostMapping("entrance")
    public String login(@RequestParam("email") String email,
                        @RequestParam("pw") String pw,
                        Model model, HttpSession session) {
        User user = userService.login(email, pw);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/wishes";
        }
        return "redirect:/login";

    }

    @GetMapping("login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/wishes")
    public String getWishes(@PathVariable int id, Model model) {
        List<Wish> wishes = wishlistService.getWishes();
        model.addAttribute("wishes", wishes);
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "wishlist";
    }

    @GetMapping("/createUser")
    public String showCreateUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";
    }


}
