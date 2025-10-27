package com.example.wishlist.Controller;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Service.UserService;
import com.example.wishlist.Service.WishlistService;
import org.springframework.stereotype.Controller;
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

    @PostMapping("login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("pw") String pw,
                        Model model) {
        User user = userService.login(email, pw);
        if (user != null) {
            return "redirect:/wishes/" + user.getId() ;
        }
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

    @PostMapping("/{wishId}/wishes")
    public String deleteWish(@PathVariable int wishId){
        wishlistService.deleteWish(wishId);
        return "redirect:/{id}/wishes";
    }
 // Du skal lave postmapping der tjekker om brugeren allerede eksistere,
    // hvis den gør, så får man en fejlmeddelse og hvis ikke, så viderestilles man til login-siden

    @PostMapping("/{userId}/user")
    public String createUser(@PathVariable int userId){
       if(userId : us)
        userService
    }

}
