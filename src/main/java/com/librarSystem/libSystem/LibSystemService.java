package com.librarSystem.libSystem;

import com.librarSystem.books.BooksService;
import com.librarSystem.exception.ResourceNotFound;
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

    public ArrayList<String> getBookTitlesLoanedByUser(String username) {
        return libSystemDAO.getBookTitlesLoanedByUser(username);
    }

    public ArrayList<String> getAuthorsLoanedByUser(String author) {
        return libSystemDAO.getAuthorsLoanedByUser(author);
    }

    public ArrayList<String> getBookFormatLoanedByUser(String bookFormat) {
        return libSystemDAO.getBookFormatLoanedByUser(bookFormat);
    }

    public void updateCopyNumbers (String title, String author, String bookFormat){
        ArrayList<String> checkIfLoaned = selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat);
        if(checkIfLoaned.size() > 0){
            int availableCopies = Integer.parseInt(booksService.checkTotalCopies(title, author, bookFormat).toString()
                    .replace("[", "").replace("]", "")) - checkIfLoaned.size();
            booksService.updateAvailableCopies(title, author, availableCopies, bookFormat);
            booksService.updateCopiesInUse(title, author, checkIfLoaned.size(), bookFormat);
        } else {
            int availableCopies = Integer.parseInt(booksService.checkTotalCopies(title, author, bookFormat).toString()
                    .replace("[", "").replace("]", "")) - checkIfLoaned.size();
            booksService.updateAvailableCopies(title, author, availableCopies, bookFormat);
            booksService.updateCopiesInUse(title, author,0, bookFormat);
        }
    }

    public void returnBook(int id, String title, String author, String bookFormat){
        if(!libSystemDAO.selectLoansById(id).isEmpty() &&
                !selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat).isEmpty()){
            libSystemDAO.returnBook(id, title, author, bookFormat);
            updateCopyNumbers(title, author, bookFormat);
        } else if (libSystemDAO.selectLoansById(id).isEmpty() ||
                !selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat).isEmpty()){
            throw new ResourceNotFound("no loan with id " + id + " for " + title + " by " +author+ "exists");
        }
    }

    public void borrowBook(String username, String title, String author, String bookFormat){
        int totalCopies = Integer.parseInt(booksService.checkTotalCopies(title, author, bookFormat).toString()
                .replace("[", "").replace("]", ""));
        int totalLoaned = selectLoansByTitleAndAuthorAndBookFormat(title, author, bookFormat).size();
        ArrayList<String> titlesLoanedByUser = getBookTitlesLoanedByUser(username);
        ArrayList<String> authorsLoanedByUser = getAuthorsLoanedByUser(username);
        ArrayList<String> formatLoanedByUser = getBookFormatLoanedByUser(username);
        boolean checkIfTitleOnLoan = titlesLoanedByUser.stream().anyMatch(title::equalsIgnoreCase);
        boolean checkIfAuthorOnLoan = authorsLoanedByUser.stream().anyMatch(author::equalsIgnoreCase);
        boolean checkIfFormatOnLoan = formatLoanedByUser.stream().anyMatch(bookFormat::equalsIgnoreCase);



        if(titlesLoanedByUser.size() > 6){
            throw new ResourceNotFound("You've reached your borrow limit, please return a book before trying again");
        }
        else if (totalCopies == totalLoaned){
            throw new ResourceNotFound("no more copies available for loan, please try again later");
        } else if (checkIfTitleOnLoan && checkIfAuthorOnLoan && checkIfFormatOnLoan) {
            throw new ResourceNotFound("you're already borrowing the " +bookFormat+ " of " +title+ " by " +author);
        }

        else{
            int idOfUser = Integer.parseInt(usersService.getUserId(username).toString()
                    .replace("[", "").replace("]", ""));
            int idOfBook = Integer.parseInt(booksService.findBookId(title, author, bookFormat).toString()
                    .replace("[", "").replace("]", ""));
            libSystemDAO.borrowBook(username, title, idOfUser, idOfBook);

            updateCopyNumbers(title, author, bookFormat);
        }
    }

// fix error when two books with same title. - make sure everything checks for bookformat and not just title.

    // add logic to allow users to pick format of book
    // maybe add a tbr and read table
    // could also add a reviews function;

}
