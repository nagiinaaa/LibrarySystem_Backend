package com.librarSystem.libSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/loan/{id}")
    public List<LibSystem> selectLoanById(@PathVariable("id") int id){
        return libSystemService.selectLoanById(id);
    }

    @DeleteMapping("{id}/{username}/{title}/{author}/{bookFormat}")
    public void returnBook(@PathVariable("id") int id, @PathVariable("username") String username, @PathVariable("title") String title,  @PathVariable("author") String author, @PathVariable("bookFormat") String bookFormat){
        libSystemService.returnBook(id, username, title, author, bookFormat);
    }

    @PostMapping("{username}/{title}/{author}/{bookFormat}")
    public void borrowBook(@PathVariable("username") String username, @PathVariable("title") String title,
                         @PathVariable("author") String author, @PathVariable("bookFormat") String bookFormat){
        libSystemService.borrowBook(username, title, author, bookFormat);
    }


}
