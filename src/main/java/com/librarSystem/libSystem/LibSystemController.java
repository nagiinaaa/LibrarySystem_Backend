package com.librarSystem.libSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "libSystem")
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

    @GetMapping("{username}")
    public List<LibSystem> selectLoansByUser(@PathVariable("username") String username){
        return libSystemService.selectLoansByUser(username);
    }
}
