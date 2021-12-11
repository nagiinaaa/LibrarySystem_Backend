package com.librarSystem.loanSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "loans")
public class LoanSystemController {

    private LoanSystemService loanSystemService;

    @Autowired
    public LoanSystemController(LoanSystemService loanSystemService) {
        this.loanSystemService = loanSystemService;
    }

    @GetMapping
    public List<LoanSystem> checkAllLoans(){
        return loanSystemService.checkAllLoans();
    }

    @GetMapping("/user/{username}")
    public List<LoanSystem> selectLoansByUser(@PathVariable("username") String username){
        return loanSystemService.selectLoansByUser(username);
    }

    @GetMapping("/loan/{id}")
    public List<LoanSystem> selectLoanById(@PathVariable("id") int id){
        return loanSystemService.selectLoanById(id);
    }

    @DeleteMapping("{id}/{username}/{title}/{author}/{bookFormat}")
    public void returnBook(@PathVariable("id") int id, @PathVariable("username") String username, @PathVariable("title") String title,  @PathVariable("author") String author, @PathVariable("bookFormat") String bookFormat){
        loanSystemService.returnBook(id, username, title, author, bookFormat);
    }

    @PostMapping("{username}/{title}/{author}/{bookFormat}")
    public void borrowBook(@PathVariable("username") String username, @PathVariable("title") String title,
                         @PathVariable("author") String author, @PathVariable("bookFormat") String bookFormat){
        loanSystemService.borrowBook(username, title, author, bookFormat);
    }
}
