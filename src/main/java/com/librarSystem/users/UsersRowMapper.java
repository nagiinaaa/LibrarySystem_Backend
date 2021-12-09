package com.librarSystem.users;

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
                rs.getString("librarian")
        );
        return users;
    }
}
