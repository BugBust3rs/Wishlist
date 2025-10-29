package com.example.wishlist.Controller;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Service.UserService;
import com.example.wishlist.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("wishhub")
@Controller
public class WishlistController {

    private final WishlistService wishlistService;
    private final UserService userService;

    public WishlistController(WishlistService wishlistService, UserService userService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
    }

    @GetMapping("/landingPage")
    public String landingPage(){
        return "landingPage";
    }

    @PostMapping("entrance")
    public String login(@ModelAttribute User user, Model model) {
        User u1 = userService.login(user.getEmail(), user.getPassword());
        if (u1 != null) {
            return "redirect:/wishes/" + u1.getId();
        }
        model.addAttribute("fejlmeddelse", "Forkert email eller adgangskode");
        return "redirect:/wishhub/login";

    }

    @GetMapping("login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/wishes/{userId}")
    public String getWishes(@PathVariable int userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        List<Wish> wishes = wishlistService.getWishesFromUser(userId);
        model.addAttribute("wishes", wishes);
        return "wishlist";
    }

    @GetMapping("/createUser")
    public String showCreateUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping("/{wishId}/wishes")
    public String deleteWish(@PathVariable int wishId) {
        wishlistService.deleteWish(wishId);
        return "redirect:/{id}/wishes";
    }


//    @PostMapping("/{userId}/user")
//    public String createUser(@PathVariable int userId){
//       if(userId : us)
//        userService
//    }

}
