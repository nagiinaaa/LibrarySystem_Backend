package com.librarSystem.libSystem;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("libSystem")
public class LibSystemDataAccessService implements LibSystemDAO {

    private JdbcTemplate jdbcTemplate;

    public LibSystemDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

    public List<LibSystem> selectLoansByUser(String username){
        String sql = """
                SELECT * FROM libSystem
                INNER JOIN users
                ON users.id = libSystem.userid
                INNER JOIN books
                ON books.id = libSystem.bookid
                WHERE username = ?
                """;
        return jdbcTemplate.query(sql, new LibSystemRowMapper(), username);
    }
}
