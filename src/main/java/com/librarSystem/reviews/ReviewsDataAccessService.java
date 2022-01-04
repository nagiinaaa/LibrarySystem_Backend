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

    @Override
    public int addReview (int userid, int bookid, Reviews reviews){
        String sql = """
                INSERT INTO reviews (userid, bookid, review, rating)
                VALUES (?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql, userid, bookid, reviews.getReview(), reviews.getRating());
    }

    @Override
    public int editReview (int id, Reviews reviews) {
        String sql = """
                UPDATE reviews SET review = ?, rating = ?
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, reviews.getReview(), reviews.getRating(), id);
    }

    @Override
    public int deleteReview (int id){
        String sql = """
                DELETE FROM reviews 
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);

    }
}
