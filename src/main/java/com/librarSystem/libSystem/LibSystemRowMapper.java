package com.librarSystem.libSystem;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibSystemRowMapper implements RowMapper<LibSystem> {

    @Override
    public LibSystem mapRow(ResultSet rs, int rowNum) throws SQLException {
        LibSystem libSystem = new LibSystem(
                rs.getString("username"),
                rs.getString("title"),
                rs.getString("author")
        );
        return libSystem;
    }
}

