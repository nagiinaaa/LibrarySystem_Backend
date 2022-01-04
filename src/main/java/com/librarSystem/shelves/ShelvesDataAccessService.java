package com.librarSystem.shelves;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("shelves")
public class ShelvesDataAccessService implements ShelvesDAO {

    private JdbcTemplate jdbcTemplate;

    public ShelvesDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Shelves> getUsersReadShelf (int userid) {
        String sql = """
                SELECT * FROM shelves 
                INNER JOIN users
                ON users.id = shelves.userid
                INNER JOIN books
                ON books.id = shelves.bookid
                WHERE userid = ? AND readBook = 'yes'
                """;
        return jdbcTemplate.query(sql, new ShelvesRowMapper(), userid);

    }

    @Override
    public List<Shelves> getUsersTBR (int userid){
        String sql = """
                SELECT * FROM shelves
                INNER JOIN users
                ON users.id = shelves.userid
                INNER JOIN books
                ON books.id = shelves.bookid
                WHERE userid = ? AND toBeRead = 'yes'
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
    public Optional<Shelves> selectShelfById (int id){
        String sql = """
                SELECT * FROM shelves WHERE id = ?
                """;
        return jdbcTemplate.query(sql, new ShelvesRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public int addToReadShelf (int userid, int bookid){
        String sql = """
                INSERT INTO shelves (userid, bookid, readBook)
                VALUES (?, ?, 'yes')
                """;
        return jdbcTemplate.update(sql, userid, bookid);
    }

    @Override
    public int addToTBR (int userid, int bookid) {
        String sql = """
                INSERT INTO shelves (userid, bookid, toBeRead)
                VALUES (?, ?, 'yes')
                """;
        return jdbcTemplate.update(sql, userid, bookid);
    }

    @Override
    public int removeFromReadShelf (int id) {
        String sql = """
                DELETE FROM shelves 
                WHERE id = ? AND readBook = 'yes'
                """;
        return jdbcTemplate.update(sql, id);

    }

    @Override
    public int removeFromTBR (int id) {
        String sql = """
                DELETE FROM shelves 
                WHERE id = ? AND toBeRead = 'yes'
                """;
        return jdbcTemplate.update(sql, id);
    }

}
