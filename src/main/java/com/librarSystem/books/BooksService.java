package com.librarSystem.books;

import com.librarSystem.exception.ResourceNotFound;
import com.librarSystem.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    private BooksDAO booksDAO;
    private UsersService usersService;

    @Autowired
    public BooksService(BooksDAO booksDAO, UsersService usersService) {
        this.booksDAO = booksDAO;
        this.usersService = usersService;
    }

    public List<Books> getAllBooks(){
        if(booksDAO.getAllBooks().isEmpty()){
            throw new ResourceNotFound("no books found");
        }
        return booksDAO.getAllBooks();
    }

    public List<Books> getBooksByTitle(String title){
        if(booksDAO.getBooksByTitle(title).isEmpty()){
            throw new ResourceNotFound("no books with the title " +title+ " found");
        }
        return booksDAO.getBooksByTitle(title);
    }

    public List<Books> getBooksByAuthor(String author){
        if(booksDAO.getBooksByAuthor(author).isEmpty()){
            throw new ResourceNotFound("no books by " + author + " found");
        }
        return booksDAO.getBooksByAuthor(author);
    }

    public Object checkTotalCopies(String title, String author, String bookFormat){
        return booksDAO.checkTotalCopies(title, author, bookFormat);
    }

    public Object findBookId(String title, String author, String bookFormat){
        if(booksDAO.findBookId(title, author, bookFormat) == null){
            throw new ResourceNotFound("no books with the title " +title+ " by " +author+ " found in " +bookFormat+ "format");
        }
        return booksDAO.findBookId(title, author, bookFormat);
    }

    // add logic to update, delete and add books so only a librarian can use them.
    // add check if librarian method in users.

//    public int deleteBook (int id, String username){
//
//    }

    public int updateAvailableCopies(String title, String author, int copies, String bookFormat){
        return booksDAO.updateAvailableCopies(title, author, copies, bookFormat);
    }

    public int updateCopiesInUse(String title, String author, int copies, String bookFormat){
        return booksDAO.updateCopiesInUse(title, author, copies, bookFormat);
    }

}
