package com.librarSystem.libSystem;

import com.librarSystem.books.BooksService;
import com.librarSystem.exception.ResourceNotFound;
import com.librarSystem.users.Users;
import com.librarSystem.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class LibSystemService {

    private LibSystemDAO libSystemDAO;
    private BooksService booksService;
    private UsersService usersService;

    @Autowired
    public LibSystemService(LibSystemDAO libSystemDAO, BooksService booksService, UsersService usersService) {
        this.libSystemDAO = libSystemDAO;
        this.booksService = booksService;
        this.usersService = usersService;
    }

    public List<LibSystem> checkAllLoans(){
        return libSystemDAO.checkAllLoans();
    }

    public List<LibSystem> selectLoansByUser(String username){
        return libSystemDAO.selectLoansByUser(username);
    }

    public ArrayList<String> selectLoansByTitleAndAuthorAndBookFormat(String title, String author, String bookFormat){
        return libSystemDAO.selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat);
    }

    public List<LibSystem> selectLoanById(int id){
        return libSystemDAO.selectLoansById(id);
    }

    public ArrayList<String> selectLoansByTitleAndAuthorAndBookFormatAndUser(String title, String author,
                                                                             String bookFormat, String username){
        return libSystemDAO.selectLoansByTitleAndAuthorAndBookFormatAndUser(title, author, bookFormat, username);
    }

    public void updateCopyNumbers (String title, String author, String bookFormat){
        ArrayList<String> checkBookLoaned = selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat);
        if(checkBookLoaned.size() > 0){
            int availableCopies = Integer.parseInt(booksService.checkTotalCopies(title, author, bookFormat).toString()
                    .replace("[", "").replace("]", "")) - checkBookLoaned.size();
            booksService.updateAvailableCopies(title, author, availableCopies, bookFormat);
            booksService.updateCopiesInUse(title, author, checkBookLoaned.size(), bookFormat);

        } else {
            int availableCopies = Integer.parseInt(booksService.checkTotalCopies(title, author, bookFormat).toString()
                    .replace("[", "").replace("]", "")) - checkBookLoaned.size();
            booksService.updateAvailableCopies(title, author, availableCopies, bookFormat);
            booksService.updateCopiesInUse(title, author,0, bookFormat);
        }
    }

    public void updateLoanNumbers (String username){
        List<LibSystem> checkLoansNumber = selectLoansByUser(username);
        if(checkLoansNumber.size() > 0) {
            int remainingLoans = Integer.parseInt(usersService.checkTotalLoans(username).toString().
                    replace("[", "").replace("]", "")) - checkLoansNumber.size();
            usersService.updateLoans(username, checkLoansNumber.size(), remainingLoans);

        } else {
            int remainingLoans = Integer.parseInt(usersService.checkTotalLoans(username).toString().
                    replace("[", "").replace("]", ""));
            usersService.updateLoans(username, 0, remainingLoans);
        }
    }

    public void returnBook(int id, String username, String title, String author, String bookFormat){
        if(!libSystemDAO.selectLoansById(id).isEmpty() &&
                !selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat).isEmpty()){
            libSystemDAO.returnBook(id);
            updateCopyNumbers(title, author, bookFormat);
            updateLoanNumbers(username);
        } else if (libSystemDAO.selectLoansById(id).isEmpty() ||
                !selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat).isEmpty()){
            throw new ResourceNotFound("no loan with id " + id + " for " + title + " by " +author+ "exists");
        }
    }

    public void borrowBook(String username, String title, String author, String bookFormat){
        int totalCopies = Integer.parseInt(booksService.checkTotalCopies(title, author, bookFormat).toString()
                .replace("[", "").replace("]", ""));
        int totalLoaned = selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat).size();

        ArrayList<String> checkIfLoanedAlready = selectLoansByTitleAndAuthorAndBookFormatAndUser(title, author, bookFormat,
                username);

        int checkLoanSlots = Integer.parseInt(usersService.checkTotalLoans(username).toString()
                .replace("[", "").replace("]", ""));
        List<LibSystem> checkCurrentLoans = selectLoansByUser(username);


        if(checkCurrentLoans.size() >= checkLoanSlots){
            throw new ResourceNotFound("You've reached your borrow limit, please return a book before trying again");
        }
        else if (totalCopies == totalLoaned){
            throw new ResourceNotFound("no more copies available for loan, please try again later");
        } else if (checkIfLoanedAlready.size() > 0) {
            throw new ResourceNotFound("you're already borrowing the " +bookFormat+ " of " +title+ " by " +author);
        }

        else{
            int idOfUser = Integer.parseInt(usersService.getUserId(username).toString()
                    .replace("[", "").replace("]", ""));
            int idOfBook = Integer.parseInt(booksService.findBookId(title, author, bookFormat).toString()
                    .replace("[", "").replace("]", ""));
            libSystemDAO.borrowBook(username, title, idOfUser, idOfBook);

            updateCopyNumbers(title, author, bookFormat);
            updateLoanNumbers(username);
        }
    }


    // maybe add a tbr and read table
    // could also add a reviews function;

}
