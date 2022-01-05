package com.librarySystem.books;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksRowMapper implements RowMapper<Books> {

    @Override
    public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
        Books books = new Books(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("bookFormat"),
                rs.getInt("numberOfCopies"),
                rs.getInt("copiesInUse"),
                rs.getInt("copiesAvailable"),
                rs.getString("bookCover")
        );
        return books;
    }
}




