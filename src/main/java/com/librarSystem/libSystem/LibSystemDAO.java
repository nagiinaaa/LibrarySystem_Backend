package com.librarSystem.libSystem;

import java.util.ArrayList;
import java.util.List;

public interface LibSystemDAO {

    List<LibSystem> checkAllLoans();
    List<LibSystem> selectLoansByUser(String username);
    ArrayList<String> selectLoansByTitleAndAuthorAndBookFormat (String title, String author, String bookFormat);
    List<LibSystem> selectLoansById(int id);
    ArrayList<String> selectLoansByTitleAndAuthorAndBookFormatAndUser (String title, String author, String bookFormat,
                                                                       String username);
    int returnBook(int id);
    int borrowBook(String username, String title, int userid, int bookid);
}
