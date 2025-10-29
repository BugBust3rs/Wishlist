package com.example.wishlist.Controller;

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
import org.springframework.web.bind.annotation.*;


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

    private boolean isLoggedIn(HttpSession session) {
        session.getAttribute("user");
        return session.getAttribute("user") != null;
    }

    @GetMapping("/landingPage")
    public String landingPage(){
        return "landingPage";
    }

    @PostMapping("entrance")
    public String login(@ModelAttribute User user,
                        HttpSession session) {
        User u1 = userService.login(user.getEmail(), user.getPassword());
        if (u1 != null) {
            session.setAttribute("user", u1);
            session.setMaxInactiveInterval(600);
            return "redirect:/wishes/" + u1.getId() ;
        }
        return "redirect:/wishhub/login";

    }

    @GetMapping("login")
    public String login(HttpSession session) {
        User user = new User();
        session.setAttribute("user", user);
        return "login";
    }

    @GetMapping("/wishes/{userId}")
    public String getWishes(@PathVariable int userId, Model model, HttpSession session) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        List<Wish> wishes = wishlistService.getWishesFromUser(userId);
        model.addAttribute("wishes", wishes);
        return isLoggedIn(session) ? "wishlist" : "login";
    }

    @GetMapping("/createUser")
    public String showCreateUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";
    }

    @DeleteMapping("deleteWish/{wishId}")
    public String deleteWish(@PathVariable int wishId, HttpSession session){
        if (!isLoggedIn(session)){
            return "login";
        }
        wishlistService.deleteWish(wishId);
        User user = (User)session.getAttribute("user");
        return "redirect:/wishes/" + user.getId();
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        if (userService.doesUserExists(user.getEmail())){
            return "redirect:/createUser";
        }
        userService.createUser(user);
        return "redirect:/login";

    }

    @GetMapping("addWish")
    public String addWish(Model model, HttpSession session){
        Wish wish = new Wish();
        model.addAttribute("wish", wish);

        return isLoggedIn(session) ? "addWish" : "login";
    }



    @PostMapping("saveWish")
    public String saveWish(@ModelAttribute Wish wish, HttpSession session){
        if (!isLoggedIn(session)){
            return "login";
        }
        User user = (User)session.getAttribute("user");
        wishlistService.saveWish(wish);
        return "redirect:/wishes/" + user.getId();
    }

    @PutMapping("updateWish/{wishId}")
    public String updateWish(@PathVariable int wishId, Model model, HttpSession session){
        if (!isLoggedIn(session)){
            return "login";
        }
        Wish wish = wishlistService.getWishFromWishId(wishId);
        model.addAttribute("wish", wish);
        return "updateWish";
    }
}
