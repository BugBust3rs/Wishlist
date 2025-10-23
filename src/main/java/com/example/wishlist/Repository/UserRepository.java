package com.example.wishlist.Repository;

import com.example.wishlist.Model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

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
}
