package com.example.wishlist.Controller;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Repository.UserRepository;
import com.example.wishlist.Service.UserService;
import com.example.wishlist.Service.WishlistService;
import com.example.wishlist.exception.ApiRequestException;
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
    private final UserRepository userRepository;


    public WishlistController(WishlistService wishlistService, UserService userService, UserRepository userRepository) {
        this.wishlistService = wishlistService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    private boolean isLoggedIn(HttpSession session) {
        session.getAttribute("user");
        return session.getAttribute("user") != null;
    }

    @GetMapping("/landingPage")
    public String landingPage() {
        return "landingPage";
    }

    @PostMapping("entrance")
    public String login(@ModelAttribute User user,
                        HttpSession session) {
        User u1 = userService.login(user.getEmail(), user.getPassword());
        if (u1 != null) {
            session.setAttribute("user", u1);
            session.setMaxInactiveInterval(600);
            return "redirect:/wishhub/wishes";
        }
        return "redirect:/wishhub/login";

    }

    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/wishhub/landingPage";
    }

    @GetMapping("/wishes/{wishlistId}")
    public String getWishes(@PathVariable int wishlistId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Wishlist wishlist = wishlistService.getWishlist(wishlistId);
        // lav et tjek om user.getuserid == wishlist.getuserid, hvis det er false redirect til login
        if (!isLoggedIn(session) || user.getId() != wishlist.getUserId()) {
            return "redirect:/wishhub/login";
        }

        user.setChosenWhislist(wishlist.getWishlistId());
        session.setAttribute("user", user);

        model.addAttribute("user", user);
        List<Wish> wishes = wishlistService.getWishesFromUser(wishlist.getWishlistId());
        model.addAttribute("wishes", wishes);
        return "wishlist";
    }


    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }


    @GetMapping("/createUser")
    public String showCreateUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping("deleteWish/{wishId}")
    public String deleteWish(@PathVariable int wishId, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/wishhub/login";
        }
        wishlistService.deleteWish(wishId);
        return "redirect:/wishhub/wishes";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        if (userService.doesUserExists(user.getEmail())) {
            return "redirect:/wishhub/createUser";
        }
        userService.createUser(user);
        return "redirect:/wishhub/login";

    }

    @GetMapping("addWish")
    public String addWish(Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/wishhub/login";
        }
        Wish wish = new Wish();
        User user = (User) session.getAttribute("user");
        wish.setWishlistId(user.getChosenWhislist());
        model.addAttribute("wish", wish);

        return "addWish";
    }

    @GetMapping("addWishList")
    public String addWishlist() {
        return "redirect:/wishhub/profile";
    }


    @PostMapping("saveWish")
    public String saveWish(@ModelAttribute Wish wish, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/wishhub/login";
        }
        wishlistService.saveWish(wish);
        return "redirect:/wishhub/wishes";
    }

    @GetMapping("updateWish/{wishId}")
    public String updateWish(@PathVariable int wishId, Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/wishhub/login";
        }
        Wish wish = wishlistService.getWishFromWishId(wishId);
        model.addAttribute("wish", wish);
        return "updateWish";
    }
}
