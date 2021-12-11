package com.librarSystem.users.resultSetExtractors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TotalLoansResultSetExtractor implements ResultSetExtractor {

    @Override
    public ArrayList<Integer> extractData(ResultSet rs) throws SQLException,
            DataAccessException {
        ArrayList<Integer> totalLoans = new ArrayList<Integer>();
        while (rs.next()){
            totalLoans.add(rs.getInt("totalLoans"));
        }
        return totalLoans;
    }
}
