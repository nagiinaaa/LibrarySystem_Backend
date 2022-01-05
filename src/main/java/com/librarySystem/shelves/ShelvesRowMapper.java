package com.librarySystem.shelves;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShelvesRowMapper  implements RowMapper<Shelves> {

    @Override
    public Shelves mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shelves shelves = new Shelves(
                rs.getInt("id"),
                rs.getInt("userid"),
                rs.getString("username"),
                rs.getInt("bookid"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getBoolean("readBook"),
                rs.getBoolean("toBeRead")
        );
        return shelves;
    }
}
