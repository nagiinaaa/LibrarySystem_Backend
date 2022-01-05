package com.librarySystem.users;

import java.util.List;
import java.util.Optional;

public interface UsersDAO {
    List<Users> getAllUsers();
    Optional<Users> selectUserById(int id);
    List<Users> getUserId(String username);
    int deleteUser(int id);
    int updateUser(int id, Users users);
    int registerUser(Users users);
    Object checkTotalLoans(String username);
    int updateLoans(String username, int currentLoans, int remainingLoans);
    List<Users> checkIfLibrarian(String username);
}
