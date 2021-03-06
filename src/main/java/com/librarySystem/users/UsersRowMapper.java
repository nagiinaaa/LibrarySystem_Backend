package com.librarySystem.users;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRowMapper implements RowMapper<Users> {

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users users = new Users(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getBoolean("librarian"),
                rs.getInt("totalLoans"),
                rs.getInt("currentLoans"),
                rs.getInt("remainingLoans")
        );
        return users;
    }
}
