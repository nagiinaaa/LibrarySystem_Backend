package com.librarSystem.loanSystem;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanSystemRowMapper implements RowMapper<LoanSystem> {

    @Override
    public LoanSystem mapRow(ResultSet rs, int rowNum) throws SQLException {
        LoanSystem loanSystem = new LoanSystem(
                rs.getInt("id"),
                rs.getInt("userid"),
                rs.getString("username"),
                rs.getInt("bookid"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("bookFormat")
        );
        return loanSystem;
    }
}

