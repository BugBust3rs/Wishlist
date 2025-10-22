package com.example.wishlist.Repository;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;


    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public void addWish(Wish wish) {
        String sql = "INSERT INTO WishList (userId, name, description, price, link, isReserved) VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, wish.getUserId(), wish.getName(), wish.getDescription(), wish.getPrice(), wish.getLink(), wish.getReserved());
    }

    public void createUser(User user){
        String sql = "INSERT INTO User (name, email, password) VALUES (?,?,?)";
        jdbcTemplate.update(sql,user.getName(),user.getEmail(),user.getPassword());
    }

    public List<User> getUsers(){
        final String sql = "SELECT * FROM USER";

        return jdbcTemplate.query(sql, (rs, rownum)  -> {
           User user = new User();
           user.setId(rs.getInt("user_id"));
           user.setName(rs.getString("name"));
           user.setPassword(rs.getString("password"));
           user.setEmail(rs.getString("email"));
           return user;
        });
    }

    public void deleteWish(int id){

        String SQL = "DELETE FROM Wish WHERE wish_id = ?";
        jdbcTemplate.update(SQL, id);
    }
}
