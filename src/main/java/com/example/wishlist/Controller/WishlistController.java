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


@RequestMapping("")
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

    @PostMapping("entrance")
    public String login(@RequestParam("email") String email,
                        @RequestParam("pw") String pw,
                        HttpSession session) {
        User user = userService.login(email, pw);
        if (user != null) {
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(600);
            return "redirect:/wishes/" + user.getId() ;
        }
        return "redirect:/login";

    }

    @GetMapping("login")
    public String login(Model model, HttpSession session) {
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
        wishlistService.deleteWish(wishId);
        User user = (User)session.getAttribute("user");
        return isLoggedIn(session) ? "redirect:/wishes/" + user.getId() : "login";
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
        User user = (User)session.getAttribute("user");
        wishlistService.saveWish(wish);
        return isLoggedIn(session) ? "redirect:/wishes/" + user.getId() : "login";
    }

    @PutMapping("updateWish/{wishId}")
    public String updateWish(@PathVariable int wishId, Model model, HttpSession session){
        Wish wish = wishlistService.getWishFromWishId(wishId);
        model.addAttribute("wish", wish);
        return isLoggedIn(session) ? "updateWish" : "login";
    }
}
