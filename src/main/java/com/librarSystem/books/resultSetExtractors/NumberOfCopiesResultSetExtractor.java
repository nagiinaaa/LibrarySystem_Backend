package com.librarSystem.books.resultSetExtractors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NumberOfCopiesResultSetExtractor implements ResultSetExtractor {

    @Override
    public ArrayList<Integer> extractData(ResultSet rs) throws SQLException,
            DataAccessException {
        ArrayList<Integer> numberOfCopies = new ArrayList<Integer>();
        while (rs.next()){
            numberOfCopies.add(rs.getInt("numberOfCopies"));
        }
        return numberOfCopies;
    }
}
