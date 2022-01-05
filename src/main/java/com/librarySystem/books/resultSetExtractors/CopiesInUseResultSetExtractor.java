package com.librarySystem.books.resultSetExtractors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CopiesInUseResultSetExtractor implements ResultSetExtractor {

    @Override
    public ArrayList<Integer> extractData(ResultSet rs) throws SQLException,
            DataAccessException {
        ArrayList<Integer> copiesInUse = new ArrayList<Integer>();
        while (rs.next()){
            copiesInUse.add(rs.getInt("copiesInUse"));
        }
        return copiesInUse;
    }
}
