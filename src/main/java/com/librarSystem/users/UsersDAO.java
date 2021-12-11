package com.librarSystem.users;

import org.apache.catalina.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface UsersDAO {
    List<Users> getAllUsers();
    Optional<Users> selectUserById(int id);
    Object getUserId(String username);
    int deleteUser(int id);
    int updateUser(int id, Users users);
    int registerUser(Users users);
    Object checkTotalLoans(String username);
    int updateLoans(String username, int currentLoans, int remainingLoans);
//    int updateRemainingLoans(String username, int remainingLoans);


}
