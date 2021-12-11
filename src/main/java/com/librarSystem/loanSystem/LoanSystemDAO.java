package com.librarSystem.loanSystem;

import java.util.ArrayList;
import java.util.List;

public interface LoanSystemDAO {

    List<LoanSystem> checkAllLoans();
    List<LoanSystem> selectLoansByUser(String username);
    ArrayList<String> selectLoansByTitleAndAuthorAndBookFormat (String title, String author, String bookFormat);
    List<LoanSystem> selectLoansById(int id);
    ArrayList<String> selectLoansByTitleAndAuthorAndBookFormatAndUser (String title, String author, String bookFormat, String username);
    int returnBook(int id);
    int borrowBook(String username, String title, int userid, int bookid);
}
