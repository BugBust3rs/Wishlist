package com.example.wishlist.Repository;

import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public void addWishlist(Wishlist wishList) {
        String sql = "INSERT INTO Wishlist (name, user_id) VALUES (?, ?)";

        jdbcTemplate.update(sql, wishList.getName(), wishList.getUserId());
    }

}
