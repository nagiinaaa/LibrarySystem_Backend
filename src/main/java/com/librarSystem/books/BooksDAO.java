package com.librarSystem.books;

import java.util.List;

public interface BooksDAO {

    List<Books> getAllBooks();
    List<Books> getBooksByTitle(String title);
    List<Books> getBooksByAuthor(String author);
    Object checkTotalCopies(String title, String author, String bookFormat);
    Object findBookId(String title, String author, String bookFormat);
    int deleteBook(int id);
    int updateBook(int id, Books books);
    int addBook (Books books);
    int updateAvailableCopies (String title, String author, int copies, String bookFormat);
    int updateCopiesInUse (String title, String author, int copies, String bookFormat);
}
