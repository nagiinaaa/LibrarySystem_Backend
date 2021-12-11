package com.librarSystem.loanSystem;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("libSystem")
public class LoanSystemDataAccessService implements LoanSystemDAO {

    private JdbcTemplate jdbcTemplate;

    public LoanSystemDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<LoanSystem> checkAllLoans() {
        String sql = """
                SELECT * FROM loanSystem
                INNER JOIN users
                ON users.id = loanSystem.userid
                INNER JOIN books
                ON books.id = loanSystem.bookid
                """;
        return jdbcTemplate.query(sql, new LoanSystemRowMapper());
    }

    @Override
    public List<LoanSystem> selectLoansByUser(String username) {
        String sql = """
                SELECT * FROM loanSystem
                INNER JOIN users
                ON users.id = loanSystem.userid
                INNER JOIN books
                ON books.id = loanSystem.bookid
                WHERE lower(username) = ?
                """;
        return jdbcTemplate.query(sql, new LoanSystemRowMapper(), username);
    }

    @Override
    public List<LoanSystem> selectLoansById(int id) {
        String sql = """
                SELECT * FROM loanSystem
                INNER JOIN users
                ON users.id = loanSystem.userid
                INNER JOIN books
                ON books.id = loanSystem.bookid
                WHERE loanSystem.id = ?
                """;
        return jdbcTemplate.query(sql, new LoanSystemRowMapper(), id);
    }

    @Override
    public ArrayList<String> selectLoansByTitleAndAuthorAndBookFormat(String title, String author, String bookFormat) {
        String sql = """
                SELECT title FROM loanSystem
                INNER JOIN users
                ON users.id = loanSystem.userid
                INNER JOIN books
                ON books.id = loanSystem.bookid
                WHERE lower(books.title) = ? AND lower(books.author) = ? AND lower(books.bookFormat) = ?
                """;
        return (ArrayList<String>) jdbcTemplate.query(sql, new LoanSystemResultSetExtractor(), title, author, bookFormat);
    }

    @Override
    public ArrayList<String> selectLoansByTitleAndAuthorAndBookFormatAndUser (String title, String author, String bookFormat,
                                                                              String username) {
        String sql = """
                SELECT title FROM loanSystem
                INNER JOIN users
                ON users.id = loanSystemm.userid
                INNER JOIN books
                ON books.id = loanSystem.bookid
                WHERE lower(books.title) = ? AND lower(books.author) = ? AND lower(books.bookFormat) = ? 
                AND users.username = ?
                """;
        return (ArrayList<String>) jdbcTemplate.query(sql, new LoanSystemResultSetExtractor(), title, author,
                bookFormat, username);
    }

    @Override
    public int returnBook(int id) {
        String sql = """
                DELETE FROM loanSystem
                WHERE loanSystem.id = ? 
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int borrowBook(String username, String title, int userid, int bookid) {
        String sql = """
                INSERT INTO loanSystem (userid, bookid)
                VALUES (?, ?);
                """;
        return jdbcTemplate.update(sql, userid, bookid);
    }

}
