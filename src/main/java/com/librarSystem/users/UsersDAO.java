package com.librarSystem.users;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface UsersDAO {
    List<Users> getAllUsers();
    Optional<Users> selectUserById(int id);


}
