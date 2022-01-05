package com.librarySystem.books;

import com.librarySystem.users.Users;
import com.librarySystem.users.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class BooksServiceTest {

    private BooksDAO booksDAO;
    private UsersService usersService;
    private BooksService underTest;

    @BeforeEach
    void setUp() {
        booksDAO = mock(BooksDAO.class);
        usersService = mock(UsersService.class);
        underTest = new BooksService(booksDAO, usersService);
    }

    @Test
    void getAllBooks() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");

        List<Books> bookList = List.of(books);
        when(booksDAO.getAllBooks()).thenReturn(bookList);

        int actual = underTest.getAllBooks().size();
        assertThat(actual).isEqualTo(1);

    }

    @Test
    void checkNoBooksFound() {
        assertThatThrownBy(() -> underTest.getAllBooks())
                .isInstanceOf(Exception.class)
                .hasMessageContaining("no books found");
    }

    @Test
    void getBooksByTitle() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");

        List<Books> bookList = List.of(books);
        when(booksDAO.getBooksByTitle("The Diviners")).thenReturn(bookList);

        int actual = underTest.getBooksByTitle("The Diviners").size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void checkNoBookWithTitleFound() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");

        List<Books> bookList = List.of(books);
        when(booksDAO.getBooksByTitle("The Diviners")).thenReturn(bookList);

        assertThatThrownBy(() -> underTest.getBooksByTitle("Harry Potter"))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("no books with the title Harry Potter found");
    }

    @Test
    void getBooksByAuthor() {

        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");

        List<Books> bookList = List.of(books);
        when(booksDAO.getBooksByAuthor("Libba Bray")).thenReturn(bookList);

        int actual = underTest.getBooksByAuthor("Libba Bray").size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void checkNoBookByAuthorFound() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");

        List<Books> bookList = List.of(books);
        when(booksDAO.getBooksByAuthor("Libba Bray")).thenReturn(bookList);

        assertThatThrownBy(() -> underTest.getBooksByAuthor("Stephen King"))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("no books by Stephen King found");
    }


    @Test
    void getBooksById() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");

        List<Books> bookList = List.of(books);
        when(booksDAO.getBooksById(1)).thenReturn(bookList);

        int actual = underTest.getBooksById(1).size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void checkNoBookWithId() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");

        List<Books> bookList = List.of(books);
        when(booksDAO.getBooksById(1)).thenReturn(bookList);

        assertThatThrownBy(() -> underTest.getBooksById(2))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("no books with the id 2 found");
    }

    @Test
    void checkTotalCopies() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");

        when(booksDAO.checkTotalCopies("The Diviners", "Libba Bray", "eBook")).thenReturn(2);

        int actual = Integer.parseInt(underTest.checkTotalCopies("The Diviners", "Libba Bray", "eBook").toString()
                .replace("[", "").replace("]", ""));
        assertThat(actual).isEqualTo(2);
    }

    @Test
    void checkCopiesInUse() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");

        List<Books> bookList = List.of(books);
        when(booksDAO.checkCopiesInUse(1)).thenReturn(0);

        int actual = Integer.parseInt(underTest.checkCopiesInUse(1).toString()
                .replace("[", "").replace("]", ""));
        assertThat(actual).isEqualTo(0);
    }

    @Test
    void findBookId() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");

        when(booksDAO.findBookId("The Diviners", "Libba Bray", "eBook")).thenReturn(1);

        int actual = Integer.parseInt(underTest.findBookId("The Diviners", "Libba Bray", "eBook")
                .toString().replace("[", "").replace("]", ""));

        assertThat(actual).isEqualTo(1);

    }

    @Test
    void noIdFoundBecauseBookDoesntExist() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");

        when(booksDAO.findBookId("The Diviners", "Libba Bray", "eBook")).thenReturn(1);

        assertThatThrownBy(() -> underTest.findBookId("Harry Potter", "J.K. Rowling", "eBook"))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("no books with the title Harry Potter by J.K. Rowling found in eBook format");
    }

    @Test
    void deleteBook() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Books> bookList = List.of(books);
        List<Users> userList = List.of(user);

        when(booksDAO.checkCopiesInUse(1)).thenReturn(0);
        when(usersService.checkIfLibrarian("librarian")).thenReturn(userList);
        when(booksDAO.getBooksById(1)).thenReturn(bookList);
        when(booksDAO.deleteBook(1)).thenReturn(1);

        int checkCopies = Integer.parseInt(underTest.checkCopiesInUse(1).toString()
                .replace("[", "").replace("]", ""));
        assertThat(checkCopies).isEqualTo(0);

        int checkLibrarian = usersService.checkIfLibrarian("librarian").size();
        assertThat(checkLibrarian).isEqualTo(1);

        int checkBookExists = underTest.getBooksById(1).size();
        assertThat(checkBookExists).isEqualTo(1);

        assertThat(underTest.deleteBook("librarian", 1)).isEqualTo(1);
    }

    @Test
    void cantDeleteAsCopiesInUseExceptionCheck() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                1, 1, "placeholder");
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Books> bookList = List.of(books);
        List<Users> userList = List.of(user);


        when(usersService.checkIfLibrarian("librarian")).thenReturn(userList);
        when(booksDAO.checkCopiesInUse(1)).thenReturn(1);
        when(booksDAO.getBooksById(1)).thenReturn(bookList);
        when(booksDAO.deleteBook(1)).thenReturn(1);


        assertThatThrownBy(() -> underTest.deleteBook("librarian", 1))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("book is currently on loan, please try again later");
    }

    @Test
    void mustBeLibrarianToDeleteExceptionCheck(){
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");
        Users user = new Users(1, "librarian", "password", false, 2,
                0, 2);

        List<Books> bookList = List.of(books);


        when(booksDAO.checkCopiesInUse(1)).thenReturn(0);
        when(booksDAO.getBooksById(1)).thenReturn(bookList);
        when(booksDAO.deleteBook(1)).thenReturn(1);


        assertThatThrownBy(() -> underTest.deleteBook("librarian", 1))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("You must be a librarian to do that");
    }

    @Test
    void updateBook() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Users> userList = List.of(user);

        when(usersService.checkIfLibrarian("librarian")).thenReturn(userList);
        when(booksDAO.updateBook(1, books)).thenReturn(1);

        int checkLibrarian = usersService.checkIfLibrarian("librarian").size();
        assertThat(checkLibrarian).isEqualTo(1);
        assertEquals(1, underTest.updateBook("librarian", 1, books));

    }

    @Test
    void addBook() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");

        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Users> userList = List.of(user);

        when(usersService.checkIfLibrarian("librarian")).thenReturn(userList);
        when(booksDAO.addBook(books)).thenReturn(1);

        int checkLibrarian = usersService.checkIfLibrarian("librarian").size();
        assertThat(checkLibrarian).isEqualTo(1);

        assertEquals(1, underTest.addBook("librarian", books));
    }

    @Test
    void updateAvailableCopies() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 4,
                1, 4, "placeholder");

        when(booksDAO.updateAvailableCopies("The Diviners", "Libba Bray", 3, "eBook"))
                .thenReturn(1);

        assertEquals(1, underTest.updateAvailableCopies("The Diviners", "Libba Bray", 3,
                "eBook"));
    }

    @Test
    void updateCopiesInUse() {
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");

        when(booksDAO.updateCopiesInUse("The Diviners", "Libba Bray", 1, "eBook"))
                .thenReturn(1);

        assertEquals(1, underTest.updateCopiesInUse("The Diviners", "Libba Bray", 1,
                "eBook"));
    }
}