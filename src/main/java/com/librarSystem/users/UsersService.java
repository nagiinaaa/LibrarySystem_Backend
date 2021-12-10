package com.librarSystem.users;

import com.librarSystem.exception.ResourceNotFound;
import org.apache.catalina.User;
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

    public Object getUserId(String username){
        if(usersDAO.getUserId(username).equals(null)){
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

}
