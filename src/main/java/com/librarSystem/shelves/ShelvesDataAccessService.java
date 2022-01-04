package com.librarSystem.shelves;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("shelves")
public class ShelvesDataAccessService implements ShelvesDAO {

    private JdbcTemplate jdbcTemplate;

    public ShelvesDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Shelves> getUsersReadShelf (int userid) {
        String sql = """
                SELECT * FROM shelves WHERE userid = ? AND readBook = 'yes'
                """;
        return jdbcTemplate.query(sql, new ShelvesRowMapper(), userid);

    }

    @Override
    public List<Shelves> getUsersTBR (int userid){
        String sql = """
                SELECT * FROM shelves WHERE userid = ? AND toBeRead = 'yes'
                """;
        return jdbcTemplate.query(sql, new ShelvesRowMapper(), userid);

    }

    @Override
    public List<Shelves> checkIfOnReadShelf (int userid, int bookid){
        String sql = """
                SELECT * FROM shelves WHERE userid = ? AND bookid = ? AND readBook = 'yes'
                """;
        return jdbcTemplate.query(sql, new ShelvesRowMapper(), userid, bookid);
    }

    @Override
    public List<Shelves> checkIfOnTBR (int userid, int bookid){
        String sql = """
                SELECT * FROM shelves WHERE userid = ? AND bookid = ? AND toBeRead = 'yes'
                """;
        return jdbcTemplate.query(sql, new ShelvesRowMapper(), userid, bookid);
    }

    @Override
    public int addToReadShelf (Shelves shelves){
        String sql = """
                INSERT INTO shelves (userid, bookid, readBook)
                VALUES (?, ?, 'yes')
                """;
        return jdbcTemplate.update(sql, shelves.getUserid(), shelves.getBookid());
    }

    @Override
    public int addToTBR (Shelves shelves) {
        String sql = """
                INSERT INTO shelves (userid, bookid, toBeRead)
                VALUES (?, ?, 'yes')
                """;
        return jdbcTemplate.update(sql, shelves.getUserid(), shelves.getBookid());
    }

    @Override
    public int removeFromReadShelf (int userid, int bookid) {
        String sql = """
                DELETE FROM shelves 
                WHERE userid = ? AND bookid = ? AND readBook = 'yes'
                """;
        return jdbcTemplate.update(sql, userid, bookid);

    }

    @Override
    public int removeFromTBR (int userid, int bookid) {
        String sql = """
                DELETE FROM shelves 
                WHERE userid = ? AND bookid = ? AND toBeRead = 'yes'
                """;
        return jdbcTemplate.update(sql, userid, bookid);
    }

}
