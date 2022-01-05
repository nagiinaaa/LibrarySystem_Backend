package com.librarySystem.loanSystem;

import java.util.ArrayList;
import java.util.List;

public interface LoanSystemDAO {

    List<LoanSystem> checkAllLoans();
    List<LoanSystem> selectLoansByUser(String username);
    List<LoanSystem>  selectLoansByTitleAndAuthorAndBookFormat (String title, String author, String bookFormat);
    List<LoanSystem> selectLoanById(int id);
    List<LoanSystem> selectLoansByTitleAndAuthorAndBookFormatAndUser (String title, String author, String bookFormat, String username);
    int returnBook(int id);
    int borrowBook(String username, String title, int userid, int bookid);
    void updateCopyNumbers(String title, String author, String bookFormat);
    void updateLoanNumbers(String username);
}
