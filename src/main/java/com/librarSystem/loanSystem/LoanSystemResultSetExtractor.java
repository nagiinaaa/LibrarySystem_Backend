package com.librarSystem.loanSystem;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanSystemResultSetExtractor implements ResultSetExtractor {

    @Override
    public ArrayList<String> extractData(ResultSet rs) throws SQLException,
            DataAccessException {
        ArrayList<String> bookTitles = new ArrayList<String>();
        while (rs.next()) {
            bookTitles.add(rs.getString("title"));
        }
        return bookTitles;
    }
}
