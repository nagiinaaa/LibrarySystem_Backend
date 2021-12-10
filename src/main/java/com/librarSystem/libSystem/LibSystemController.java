package com.librarSystem.libSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "loans")
public class LibSystemController {

    private LibSystemService libSystemService;

    @Autowired
    public LibSystemController(LibSystemService libSystemService) {
        this.libSystemService = libSystemService;
    }

    @GetMapping
    public List<LibSystem> checkAllLoans(){
        return libSystemService.checkAllLoans();
    }

    @GetMapping("/user/{username}")
    public List<LibSystem> selectLoansByUser(@PathVariable("username") String username){
        return libSystemService.selectLoansByUser(username);
    }

    @GetMapping("/book/{title}")
    public List<LibSystem> selectLoansByTitle(@PathVariable("title") String title){
        return libSystemService.selectLoansByTitle(title);
    }

    @GetMapping("/loan/{id}")
    public List<LibSystem> selectLoanById(@PathVariable("id") int id){
        return libSystemService.selectLoanById(id);
    }

    @DeleteMapping("{id}/{title}")
    public void returnBook(@PathVariable("id") int id, @PathVariable("title") String title){
        libSystemService.returnBook(id, title);
    }

    @PostMapping("{username}/{title}")
    public void loanBook(@PathVariable("username") String username, @PathVariable("title") String title){
        libSystemService.loanBook(username, title);
    }

}
