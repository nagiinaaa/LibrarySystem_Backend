package com.librarSystem.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "users")
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping("{id}")
    public Optional<Users> selectUserById(@PathVariable("id") int id){
        return usersService.selectUserById(id);
    }

    @DeleteMapping("{id}")
    public int deletUser (@PathVariable("id") int id){
        return usersService.deleteUser(id);
    }

    @PutMapping("{id}")
    public int updateUser (@PathVariable("id") int id, @RequestBody Users users){
        return usersService.updateUser(id, users);
    }

    @PostMapping
    public int registerUser (@RequestBody Users users){
        return usersService.registerUser(users);
    }


}
