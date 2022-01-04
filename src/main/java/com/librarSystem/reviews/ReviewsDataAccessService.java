package com.librarSystem.reviews;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("reviews")
public class ReviewsDataAccessService implements ReviewsDAO{

    private JdbcTemplate jdbcTemplate;

    public ReviewsDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Reviews> getReviewsByBook (int bookid){
        String sql = """
                SELECT * FROM reviews
                INNER JOIN users
                ON users.id = reviews.userid
                INNER JOIN books
                ON books.id = reviews.bookid
                WHERE reviews.bookid = ?
                """;
        return jdbcTemplate.query(sql, new ReviewsRowMapper(), bookid);
    }

    @Override
    public List<Reviews> getReviewsByUser (int userid){
        String sql = """
                SELECT * FROM reviews
                INNER JOIN users
                ON users.id = reviews.userid
                INNER JOIN books
                ON books.id = reviews.bookid
                WHERE reviews.userid = ?
                """;
        return jdbcTemplate.query(sql, new ReviewsRowMapper(), userid);
    }
}
