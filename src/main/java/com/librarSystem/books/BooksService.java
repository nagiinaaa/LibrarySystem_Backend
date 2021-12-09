package com.librarSystem.books;

import com.librarSystem.exception.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    private BooksDAO booksDAO;

    public BooksService(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
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

    // add logic to update and delete books so only a librarian can use them.
}