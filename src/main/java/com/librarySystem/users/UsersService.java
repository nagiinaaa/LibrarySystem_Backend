package com.librarySystem.users;

import com.librarySystem.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private UsersDAO usersDAO;

    @Autowired
    public UsersService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;

    }

    public List<Users> getAllUsers(){
        if(usersDAO.getAllUsers().isEmpty()) {
            throw new ResourceNotFound("no users found");
        }
        return usersDAO.getAllUsers();
    }

    public Optional<Users> selectUserById(int id){
        if(usersDAO.selectUserById(id).isEmpty()) {
            throw new ResourceNotFound("user with id " +id+ " doesn't exist");
        }
        return usersDAO.selectUserById(id);
    }

    public List<Users> getUserId(String username){
        if(usersDAO.getUserId(username).isEmpty()){
            throw new ResourceNotFound(username + " doesn't exist");
        }
        return usersDAO.getUserId(username);
    }

    public int deleteUser(int id){
        if(usersDAO.selectUserById(id).isEmpty()) {
            throw new ResourceNotFound("user with id " +id+ " doesn't exist");
        }
        return usersDAO.deleteUser(id);
    }

    public int updateUser(int id, Users users){
        if(usersDAO.selectUserById(id).isEmpty()) {
            throw new ResourceNotFound("user with id " +id+ " doesn't exist");
        }
        return usersDAO.updateUser(id, users);
    }

    public int registerUser (Users users){
        return usersDAO.registerUser(users);
    }

    public Object checkTotalLoans(String username){
        return usersDAO.checkTotalLoans(username);
    }

    public int updateLoans(String username, int currentLoans, int remianingLoans) {
        return usersDAO.updateLoans(username, currentLoans, remianingLoans);
    }

    public List<Users> checkIfLibrarian (String username){
        return usersDAO.checkIfLibrarian(username);
    }

}
