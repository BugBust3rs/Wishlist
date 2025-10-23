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



    public void deleteWish(int id){

        String SQL = "DELETE FROM Wish WHERE wish_id = ?";
        jdbcTemplate.update(SQL, id);
    }

    public List<Wish> getWishes() {
        final String sql = "SELECT * FROM Wish";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Wish wish = new Wish();
            wish.setId(rs.getInt("id"));
            wish.setUserId(rs.getInt("user_id"));
            wish.setName(rs.getString("name"));
            wish.setDescription(rs.getString("description"));
            wish.setPrice(rs.getDouble("price"));
            wish.setLink(rs.getString("link"));
            wish.setReserved(rs.getBoolean("isReserved"));
            return wish;
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
