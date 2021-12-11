package com.librarSystem.users.resultSetExtractors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserIdResultSetExtractor implements ResultSetExtractor {

    @Override
    public ArrayList<Integer> extractData(ResultSet rs) throws SQLException,
            DataAccessException {
        ArrayList<Integer> id = new ArrayList<Integer>();
        while (rs.next()){
            id.add(rs.getInt("id"));
        }
        return id;
    }
}
