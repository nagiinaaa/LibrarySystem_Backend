package com.librarSystem.shelves;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShelvesRowMapper  implements RowMapper<Shelves> {

    @Override
    public Shelves mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shelves shelves = new Shelves(
                rs.getInt("id"),
                rs.getInt("userid"),
                rs.getInt("bookid"),
                rs.getBoolean("readBook"),
                rs.getBoolean("toBeRead")
        );
        return shelves;
    }
}
