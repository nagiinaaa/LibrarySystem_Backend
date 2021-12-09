package com.librarSystem.libSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibSystemService {

    private LibSystemDAO libSystemDAO;

    @Autowired
    public LibSystemService(LibSystemDAO libSystemDAO) {
        this.libSystemDAO = libSystemDAO;
    }

    public List<LibSystem> checkAllLoans(){
        return libSystemDAO.checkAllLoans();
    }

    public List<LibSystem> selectLoansByUser(String username){
        return libSystemDAO.selectLoansByUser(username);
    }
}
