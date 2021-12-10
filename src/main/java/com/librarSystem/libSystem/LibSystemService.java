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

    public List<LibSystem> selectLoansByTitle(String title){
        return libSystemDAO.selectLoansByTitle(title);
    }

    public List<LibSystem> selectLoanById(int id){
        return libSystemDAO.selectLoansById(id);
    }

    public ArrayList<String> getBookTitlesLoanedByUser(String username) {
        return libSystemDAO.getBookTitlesLoanedByUser(username);
    }

    public void updateCopyNumbers (String title){
        List<LibSystem> checkIfLoaned = libSystemDAO.selectLoansByTitle(title);
        if(checkIfLoaned.size() > 0){
            int availableCopies = Integer.parseInt(booksService.checkTotalCopies(title).toString()
                    .replace("[", "").replace("]", "")) - checkIfLoaned.size();
            booksService.updateAvailableCopies(title, availableCopies);
            booksService.updateCopiesInUse(title, checkIfLoaned.size());
        } else {
            int availableCopies = Integer.parseInt(booksService.checkTotalCopies(title).toString()
                    .replace("[", "").replace("]", "")) - checkIfLoaned.size();
            booksService.updateAvailableCopies(title, availableCopies);
            booksService.updateCopiesInUse(title, 0);
        }
    }

    // delete book from libsystem
    // update number of copies available and in use in books
    public void returnBook(int id, String title){
        if(!libSystemDAO.selectLoansById(id).isEmpty() && !libSystemDAO.selectLoansByTitle(title).isEmpty()){
            libSystemDAO.returnBook(id, title);
            updateCopyNumbers(title);
        } else if (libSystemDAO.selectLoansById(id).isEmpty() && libSystemDAO.selectLoansByTitle(title).isEmpty()){
            throw new ResourceNotFound("no loan with id " + id + " for " + title + "exists");
        }
    }

    public void loanBook(String username, String title){
        int totalCopies = Integer.parseInt(booksService.checkTotalCopies(title).toString()
                .replace("[", "").replace("]", ""));
        int totalLoaned = libSystemDAO.selectLoansByTitle(title).size();

        ArrayList<String> titlesLoanedByUser = getBookTitlesLoanedByUser(username);
        boolean checkIfOnLoan = titlesLoanedByUser.stream().anyMatch(title::equalsIgnoreCase);

        if (totalCopies == totalLoaned){
            throw new ResourceNotFound("no more copies available for loan, please try again later");
        } else if (checkIfOnLoan) {
            throw new ResourceNotFound("you're already borrowing a copy of " +title);
        }
        else{
            int idOfUser = Integer.parseInt(usersService.getUserId(username).toString()
                    .replace("[", "").replace("]", ""));
            int idOfBook = Integer.parseInt(booksService.findBookId(title).toString()
                    .replace("[", "").replace("]", ""));
            libSystemDAO.loanBook(username, title, idOfUser, idOfBook);
            updateCopyNumbers(title);
        }
    }

}
