package com.librarSystem.books;

import java.util.List;

public interface BooksDAO {

    List<Books> getAllBooks();
    List<Books> getBooksByTitle(String title);
    List<Books> getBooksByAuthor(String author);
    Object checkTotalCopies(String title);
    Object findBookId(String title);
    int deleteBook(int id);
    int updateBook(int id, Books books);
    int updateAvailableCopies (String title, int copies);
    int updateCopiesInUse (String title, int copies);
}
