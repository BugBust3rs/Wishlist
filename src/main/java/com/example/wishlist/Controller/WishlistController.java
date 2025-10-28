package com.example.wishlist.Controller;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Service.UserService;
import com.example.wishlist.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;


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
                        Model model) {
        User user = userService.login(email, pw);
        if (user != null) {
            return "redirect:/wishes/" + user.getId() ;
        }
        return "redirect:/login";

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

    @PostMapping("deleteWish/{wishId}")
    public String deleteWish(@PathVariable int wishId){
        Wish wish = wishlistService.deleteWish(wishId);
        return "redirect:/wishes/" + wish.getUserId();
    }

 // Du skal lave postmapping der tjekker om brugeren allerede eksistere,
    // hvis den gør, så får man en fejlmeddelse og hvis ikke, så viderestilles man til login-siden

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        if (userService.doesUserExists(user.getEmail())){
            return "redirect:/createUser";
        }
        userService.createUser(user);

        return "redirect:/login";

    }

    @GetMapping("addWish/{userId}")
    public String addWish(@PathVariable int userId, Model model){
//        Wish wish = new Wish();
//        wish.setUserId(userId);
//        model.addAttribute("wish", wish);
        return "addWish";
    }

    @PostMapping("saveWish")
    public String saveWish(@ModelAttribute Wish wish){
        wishlistService.saveWish(wish);
        return "redirect:/wishes/" + wish.getUserId();
    }

    @GetMapping("updateWish/{wishId}")
    public String updateWish(@PathVariable int wishId, Model model){
        Wish wish = wishlistService.getWishFromWishId(wishId);
        model.addAttribute("wish", wish);
        return "updateWish";
    }
}
