package com.librarSystem.libSystem;

import java.util.ArrayList;
import java.util.List;

public interface LibSystemDAO {

    List<LibSystem> checkAllLoans();
    List<LibSystem> selectLoansByUser(String username);
    List<LibSystem> selectLoansByTitle(String title);
    List<LibSystem> selectLoansById(int id);
    ArrayList<String> getBookTitlesLoanedByUser(String username);
    int returnBook(int id, String title);
    int loanBook(String username, String title, int userid, int bookid);
}
