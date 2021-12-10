package com.librarSystem.libSystem;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorsLoanedByUserResultSetExtractor implements ResultSetExtractor {

    @Override
    public ArrayList<String> extractData(ResultSet rs) throws SQLException,
            DataAccessException {
        ArrayList<String> authors = new ArrayList<String>();
        while (rs.next()) {
            authors.add(rs.getString("author"));
        }
        return authors;
    }
}
