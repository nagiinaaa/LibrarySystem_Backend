package com.librarSystem.users;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface UsersDAO {
    List<Users> getAllUsers();
    Optional<Users> selectUserById(int id);
    int deleteUser(int id);
    int updateUser(int id, Users users);
    int registerUser(Users users);


}
