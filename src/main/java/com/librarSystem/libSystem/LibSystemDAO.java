package com.librarSystem.libSystem;

import java.util.ArrayList;
import java.util.List;

public interface LibSystemDAO {

    List<LibSystem> checkAllLoans();
    List<LibSystem> selectLoansByUser(String username);
    ArrayList<String> selectLoansByTitleAndAuthorAndBookFormat (String title, String author, String bookFormat);
    List<LibSystem> selectLoansById(int id);
    ArrayList<String> getBookTitlesLoanedByUser(String username);
    ArrayList<String> getAuthorsLoanedByUser(String author);
    ArrayList<String> getBookFormatLoanedByUser(String bookFormat);
    int returnBook(int id, String title, String author, String bookFormat);
    int borrowBook(String username, String title, int userid, int bookid);
}
