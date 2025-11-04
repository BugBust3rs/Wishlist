package com.example.wishlist.Repository;
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
        String sql = "INSERT INTO Wishlists (name, user_id) VALUES (?, ?)";

        jdbcTemplate.update(sql, wishList.getName(), wishList.getUserId());
    }

    public void deleteWishlist(int id) {
        String SQL = "DELETE FROM Wishlists WHERE wishlist_id = ?";
        jdbcTemplate.update(SQL, id);
    }

    public List<Wishlist> getWishlists() {
        final String sql = "SELECT * FROM Wishlists";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Wishlist wishlist = new Wishlist();
            wishlist.setUserId(rs.getInt("user_id"));
            wishlist.setName(rs.getString("name"));
            wishlist.setWishlistId(rs.getInt("wishlist_id"));
            return wishlist;
        });
    }

    public void updateWishlist(Wishlist wishlist){
        String sql = """
                UPDATE Wishlists
                SET name = ?,
                user_id = ?
                WHERE wishlist_id = ?
                """;
        jdbcTemplate.update(sql,
                wishlist.getName(),
                wishlist.getUserId());

    }
}
