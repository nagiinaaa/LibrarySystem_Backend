package com.librarSystem.libSystem;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("libSystem")
public class LibSystemDataAccessService implements LibSystemDAO {

    private JdbcTemplate jdbcTemplate;

    public LibSystemDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<LibSystem> checkAllLoans(){
        String sql = """
                SELECT * FROM libSystem
                INNER JOIN users
                ON users.id = libSystem.userid
                INNER JOIN books
                ON books.id = libSystem.bookid
                """;
        return jdbcTemplate.query(sql, new LibSystemRowMapper());
    }

    @Override
    public List<LibSystem> selectLoansByUser(String username){
        String sql = """
                SELECT * FROM libSystem
                INNER JOIN users
                ON users.id = libSystem.userid
                INNER JOIN books
                ON books.id = libSystem.bookid
                WHERE lower(username) = ?
                """;
        return jdbcTemplate.query(sql, new LibSystemRowMapper(), username);
    }

    @Override
    public List<LibSystem> selectLoansByTitle(String title){
        String sql = """
                SELECT * FROM libSystem
                INNER JOIN users
                ON users.id = libSystem.userid
                INNER JOIN books
                ON books.id = libSystem.bookid
                WHERE lower(title) = ?
                """;
        return jdbcTemplate.query(sql, new LibSystemRowMapper(), title);
    }

    @Override
    public List<LibSystem> selectLoansById(int id){
        String sql = """
                SELECT * FROM libSystem
                INNER JOIN users
                ON users.id = libSystem.userid
                INNER JOIN books
                ON books.id = libSystem.bookid
                WHERE libSystem.id = ?
                """;
        return jdbcTemplate.query(sql, new LibSystemRowMapper(), id);
    }

    @Override
    public ArrayList<String> getBookTitlesLoanedByUser(String username){
        String sql = """
                SELECT title FROM libSystem
                INNER JOIN users
                ON users.id = libSystem.userid
                INNER JOIN books
                ON books.id = libSystem.bookid
                WHERE lower(username) = ?
                """;
        return (ArrayList<String>) jdbcTemplate.query(sql, new BooksLoanedByUserResultSetExtractor(), username);
    }

    @Override
    public int returnBook(int id, String title){
        String sql = """
                DELETE FROM libSystem 
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int loanBook(String username, String title, int userid, int bookid){
        String sql = """
                INSERT INTO libSystem (userid, bookid)
                VALUES (?, ?);
                """;
        return jdbcTemplate.update(sql, userid, bookid);
    }
}
