package com.librarSystem.users;

import com.librarSystem.users.resultSetExtractors.TotalLoansResultSetExtractor;
import com.librarSystem.users.resultSetExtractors.UserIdResultSetExtractor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("users")
public class UsersDataAccessService implements UsersDAO{

    private JdbcTemplate jdbcTemplate;

    public UsersDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Users> getAllUsers(){
        String sql = """
                SELECT * FROM users;
                """;
        return jdbcTemplate.query(sql, new UsersRowMapper());
    }

    @Override
    public Optional<Users> selectUserById(int id){
        String sql = """
                SELECT * FROM users
                WHERE id = ?;
                """;
        return jdbcTemplate.query(sql, new UsersRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public Object getUserId(String username){
        String sql = """
                SELECT id FROM users
                WHERE lower(username) = ?;
                """;
        return jdbcTemplate.query(sql, new UserIdResultSetExtractor(), username);
    }

    @Override
    public int deleteUser(int id){
        String sql = """
                DELETE FROM users WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateUser(int id, Users users){
        String sql = """
                UPDATE users SET username = ?, password = ?, librarian = ? WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, users.getUsername(), users.getPassword(), users.getLibrarian(), id);
    }

    @Override
    public int registerUser (Users users){
        String sql = """
                INSERT INTO users (username, password, librarian)
                VALUES (?, ?, ?)
                """;
        return jdbcTemplate.update(sql, users.getUsername(), users.getPassword(), users.getLibrarian());
    }

    @Override
    public Object checkTotalLoans(String username){
        String sql = """
                SELECT totalLoans FROM users
                WHERE lower(username) = ?;
                """;
        return jdbcTemplate.query(sql, new TotalLoansResultSetExtractor(), username);
    }

    @Override
    public int updateLoans(String username, int currentLoans, int remainingLoans){
        String sql = """
                UPDATE users SET currentLoans = ?, remainingLoans = ?
                WHERE lower(username) = ?
                """;
       return jdbcTemplate.update(sql, currentLoans, remainingLoans, username);
    }

    @Override
    public List<Users> checkIfLibrarian (String username){
        String sql = """
                SELECT * FROM users
                WHERE librarian = 'true' AND lower(username) = ?;
                """;
        return jdbcTemplate.query(sql, new UsersRowMapper(), username);

    }
}
