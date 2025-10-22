package com.example.wishlist.Repository;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        final String sql = """
                SELECT
                ta.id       AS ta_id,
                ta.email    AS ta_email
                FROM USER ta
                ORDER BY ta.id
                """;

        return jdbcTemplate.query(sql, rs -> {
            Map<Integer, User> byId = new HashMap<>();
            while(rs.next()){
                int id = rs.getInt("ta_id");
                User ta = byId.get(id);
                if (ta == null){
                    ta = new User();
                    ta.setId(id);
                    ta.setEmail(rs.getString("ta_name"));
                    byId.put(id,ta);
                }
            }
            return new ArrayList<>(byId.values());
        });
    }
    public void updateWish(Wish wish){
        String sql = """
                UPDATE Wish 
                SET name = ?, 
                description = ?,  
                price = ?, 
                link = ?, 
                isReserved = ? 
                WHERE wish_id = ?
                """;
        jdbcTemplate.update(sql,
                wish.getName(),
                wish.getDescription(),
                wish.getPrice(),
                wish.getLink(),
                wish.getReserved(),
                wish.getId());

    }

}
