package com.example.wishlist.Repository;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private final JdbcTemplate jdbcTemplate;


    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }
    
    public void addWish(Wish wish){
        String sql = "INSERT INTO WishList (userId, name, description, price, link, isReserved) VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, wish.getUserId(), wish.getName(), wish.getDescription(), wish.getPrice(), wish.getLink(), wish.getReserved());
    }
}
