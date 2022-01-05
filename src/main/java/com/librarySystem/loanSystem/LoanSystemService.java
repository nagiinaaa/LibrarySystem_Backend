package com.librarySystem.loanSystem;

import com.librarySystem.books.BooksService;
import com.librarySystem.exception.ResourceNotFound;
import com.librarySystem.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanSystemService {

    private LoanSystemDAO loanSystemDAO;
    private BooksService booksService;
    private UsersService usersService;

    @Autowired
    public LoanSystemService(LoanSystemDAO loanSystemDAO, BooksService booksService, UsersService usersService) {
        this.loanSystemDAO = loanSystemDAO;
        this.booksService = booksService;
        this.usersService = usersService;
    }

    public List<LoanSystem> checkAllLoans() {
        return loanSystemDAO.checkAllLoans();
    }

    public List<LoanSystem> selectLoansByUser(String username) {
        return loanSystemDAO.selectLoansByUser(username);
    }

    public ArrayList<String> selectLoansByTitleAndAuthorAndBookFormat(String title, String author, String bookFormat) {
        return loanSystemDAO.selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat);
    }

    public List<LoanSystem> selectLoanById(int id) {
        return loanSystemDAO.selectLoansById(id);
    }

    public ArrayList<String> selectLoansByTitleAndAuthorAndBookFormatAndUser(String title, String author,
                                                                             String bookFormat, String username) {
        return loanSystemDAO.selectLoansByTitleAndAuthorAndBookFormatAndUser(title, author, bookFormat, username);
    }

    public void updateCopyNumbers(String title, String author, String bookFormat) {
        ArrayList<String> checkBookLoaned = selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat);
        if (checkBookLoaned.size() > 0) {
            int availableCopies = Integer.parseInt(booksService.checkTotalCopies(title, author, bookFormat).toString()
                    .replace("[", "").replace("]", "")) - checkBookLoaned.size();
            booksService.updateAvailableCopies(title, author, availableCopies, bookFormat);
            booksService.updateCopiesInUse(title, author, checkBookLoaned.size(), bookFormat);

        } else {
            int availableCopies = Integer.parseInt(booksService.checkTotalCopies(title, author, bookFormat).toString()
                    .replace("[", "").replace("]", "")) - checkBookLoaned.size();
            booksService.updateAvailableCopies(title, author, availableCopies, bookFormat);
            booksService.updateCopiesInUse(title, author, 0, bookFormat);
        }
    }

    public void updateLoanNumbers(String username) {
        List<LoanSystem> checkLoansNumber = selectLoansByUser(username);
        if (checkLoansNumber.size() > 0) {
            int remainingLoans = Integer.parseInt(usersService.checkTotalLoans(username).toString().
                    replace("[", "").replace("]", "")) - checkLoansNumber.size();
            usersService.updateLoans(username, checkLoansNumber.size(), remainingLoans);

        } else {
            int remainingLoans = Integer.parseInt(usersService.checkTotalLoans(username).toString().
                    replace("[", "").replace("]", ""));
            usersService.updateLoans(username, 0, remainingLoans);
        }
    }

    public void returnBook(int id, String username, String title, String author, String bookFormat) {
        if (!loanSystemDAO.selectLoansById(id).isEmpty() &&
                !selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat).isEmpty()) {
            loanSystemDAO.returnBook(id);
            updateCopyNumbers(title, author, bookFormat);
            updateLoanNumbers(username);
        } else if (loanSystemDAO.selectLoansById(id).isEmpty() ||
                !selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat).isEmpty()) {
            throw new ResourceNotFound("no loan with id " + id + " for " + title + " by " + author + "exists");
        }
    }

    public void borrowBook(String username, String title, String author, String bookFormat) {
        int totalCopies = Integer.parseInt(booksService.checkTotalCopies(title, author, bookFormat).toString()
                .replace("[", "").replace("]", ""));
        int totalLoaned = selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat).size();

        ArrayList<String> checkIfLoanedAlready = selectLoansByTitleAndAuthorAndBookFormatAndUser(title, author, bookFormat,
                username);

        int checkLoanSlots = Integer.parseInt(usersService.checkTotalLoans(username).toString()
                .replace("[", "").replace("]", ""));
        List<LoanSystem> checkCurrentLoans = selectLoansByUser(username);


        if (checkCurrentLoans.size() >= checkLoanSlots) {
            throw new ResourceNotFound("You've reached your borrow limit, please return a book before trying again");
        } else if (totalCopies == totalLoaned) {
            throw new ResourceNotFound("no more copies available for loan, please try again later");
        } else if (checkIfLoanedAlready.size() > 0) {
            throw new ResourceNotFound("you're already borrowing the " + bookFormat + " of " + title + " by " + author);
        } else {
            int idOfUser = Integer.parseInt(usersService.getUserId(username).toString()
                    .replace("[", "").replace("]", ""));
            int idOfBook = Integer.parseInt(booksService.findBookId(title, author, bookFormat).toString()
                    .replace("[", "").replace("]", ""));
            loanSystemDAO.borrowBook(username, title, idOfUser, idOfBook);

            updateCopyNumbers(title, author, bookFormat);
            updateLoanNumbers(username);
        }

    }
}