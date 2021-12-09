package com.librarSystem.books;

import java.util.List;

public interface BooksDAO {

    List<Books> getAllBooks();
    List<Books> getBooksByTitle(String title);
    List<Books> getBooksByAuthor(String author);
    int deleteBook(int id);
    int updateBook(int id, Books books);
}
