package com.librarSystem.users;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("users")
public class UsersDataAccessService implements UsersDAO{

    private JdbcTemplate jdbcTemplate;

    public UsersDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Users> getAllUsers(){
        String sql = """
                SELECT * FROM users;
                """;
        return jdbcTemplate.query(sql, new UsersRowMapper());
    }

    @Override
    public Optional<Users> selectUserById(int id){
        String sql = """
                SELECT * FROM users
                WHERE id = ?;
                """;
        return jdbcTemplate.query(sql, new UsersRowMapper(), id)
                .stream()
                .findFirst();
    }
}
