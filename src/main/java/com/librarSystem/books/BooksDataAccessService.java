package com.librarSystem.books;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("books")
public class BooksDataAccessService implements BooksDAO {

    private JdbcTemplate jdbcTemplate;

    public BooksDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Books> getAllBooks() {
        String sql = """
                SELECT * FROM books;
                """;
        return jdbcTemplate.query(sql, new BooksRowMapper());

    }

    @Override
    public List<Books> getBooksByTitle(String title){
        String sql = """
                SELECT * FROM books
                WHERE lower(title) = ?;
                """;
        return jdbcTemplate.query(sql, new BooksRowMapper(), title);

    }

    @Override
    public List<Books> getBooksByAuthor(String author){
        String sql = """
                SELECT * FROM books
                WHERE lower(author) = ?;
                """;
        return jdbcTemplate.query(sql, new BooksRowMapper(), author);

    }

    @Override
    public int deleteBook(int id){
        String sql = """
                DELETE FROM books WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateBook(int id, Books books){
        String sql = """
                UPDATE books SET title = ?, author = ?, bookFormat = ?, 
                numberOfCopies = ?, bookCover = ?
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, books.getTitle(), books.getAuthor(), books.getBookFormat(),
                books.getNumberOfCopies(), books.getBookCover(), id);
    }
}
