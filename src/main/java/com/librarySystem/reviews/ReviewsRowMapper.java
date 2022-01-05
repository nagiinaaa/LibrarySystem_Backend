package com.librarySystem.reviews;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewsRowMapper implements RowMapper<Reviews> {

    @Override
    public Reviews mapRow(ResultSet rs, int rowNum) throws SQLException{
        Reviews reviews = new Reviews(
                rs.getInt("id"),
                rs.getInt("userid"),
                rs.getString("username"),
                rs.getInt("bookid"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("review"),
                rs.getInt("rating")
        );
        return reviews;
    }
}
