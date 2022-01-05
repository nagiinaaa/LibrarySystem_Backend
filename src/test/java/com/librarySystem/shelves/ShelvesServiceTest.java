package com.librarySystem.shelves;

import com.librarySystem.books.Books;
import com.librarySystem.books.BooksService;
import com.librarySystem.users.Users;
import com.librarySystem.users.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShelvesServiceTest {

    private ShelvesDAO shelvesDAO;
    private UsersService usersService;
    private BooksService booksService;
    private ShelvesService underTest;

    @BeforeEach
    void setUp(){
        shelvesDAO = mock(ShelvesDAO.class);
        usersService = mock(UsersService.class);
        booksService = mock(BooksService.class);
        underTest = new ShelvesService(shelvesDAO, usersService, booksService);
    }

    @Test
    void getUsersReadShelf() {
        Shelves shelves = new Shelves(1,1, "username", 1, "title", "author",
                true, false);
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Shelves> shelvesList = List.of(shelves);
        List<Users> userList = List.of(user);

        when(usersService.selectUserById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(shelvesDAO.getUsersReadShelf(1)).thenReturn(shelvesList);

        assertThat(usersService.selectUserById(1)).isNotNull();

        int actual = underTest.getUsersReadShelf(1).size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void checkExceptionWhenUserHasNoBooksOnReadShelf() {
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Users> userList = List.of(user);

        when(usersService.selectUserById(1)).thenReturn(Optional.ofNullable(userList.get(0)));

        assertThatThrownBy(() -> underTest.getUsersReadShelf(1))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("user has no books on their read shelf");
    }

    @Test
    void getUsersTBR() {
        Shelves shelves = new Shelves(1,1, "username", 1, "title", "author",
                false, true);
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Shelves> shelvesList = List.of(shelves);
        List<Users> userList = List.of(user);

        when(usersService.selectUserById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(shelvesDAO.getUsersTBR(1)).thenReturn(shelvesList);

        assertThat(usersService.selectUserById(1)).isNotNull();

        int actual = underTest.getUsersTBR(1).size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void checkExceptionWhenUserHasNoBooksOnTBR() {
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Users> userList = List.of(user);

        when(usersService.selectUserById(1)).thenReturn(Optional.ofNullable(userList.get(0)));

        assertThatThrownBy(() -> underTest.getUsersTBR(1))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("user has no books on their to be read shelf");
    }

    @Test
    void checkIfOnReadShelf() {
        Shelves shelves = new Shelves(1,1, "username", 1, "title", "author",
                true, false);
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                1, 1, "placeholder");

        List<Shelves> shelvesList = List.of(shelves);
        List<Users> userList = List.of(user);
        List<Books> bookList = List.of(books);

        when(usersService.selectUserById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(booksService.getBooksById(1)).thenReturn(bookList);
        when(shelvesDAO.checkIfOnReadShelf(1, 1)).thenReturn(shelvesList);

        assertThat(usersService.selectUserById(1)).isNotNull();

        int checkIfBookExists = booksService.getBooksById(1).size();
        assertThat(checkIfBookExists).isEqualTo(1);

        int actual = underTest.checkIfOnReadShelf(1, 1).size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void checkExceptionWhenBookIsntOnUsersReadShelf(){
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                1, 1, "placeholder");

        List<Users> userList = List.of(user);
        List<Books> bookList = List.of(books);

        when(usersService.selectUserById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(booksService.getBooksById(1)).thenReturn(bookList);

        assertThatThrownBy(() -> underTest.checkIfOnReadShelf(1, 1))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("user 1 hasn't read the book with the id 1");
    }

    @Test
    void checkIfOnTBR() {
        Shelves shelves = new Shelves(1,1, "username", 1, "title", "author",
                false, true);
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                1, 1, "placeholder");

        List<Shelves> shelvesList = List.of(shelves);
        List<Users> userList = List.of(user);
        List<Books> bookList = List.of(books);

        when(usersService.selectUserById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(booksService.getBooksById(1)).thenReturn(bookList);
        when(shelvesDAO.checkIfOnTBR(1, 1)).thenReturn(shelvesList);

        assertThat(usersService.selectUserById(1)).isNotNull();

        int checkIfBookExists = booksService.getBooksById(1).size();
        assertThat(checkIfBookExists).isEqualTo(1);

        int actual = underTest.checkIfOnTBR(1, 1).size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void checkExceptionWhenBookIsntOnUsersTBR(){
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                1, 1, "placeholder");

        List<Users> userList = List.of(user);
        List<Books> bookList = List.of(books);

        when(usersService.selectUserById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(booksService.getBooksById(1)).thenReturn(bookList);

        assertThatThrownBy(() -> underTest.checkIfOnTBR(1, 1))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("book with the id 1 isn't on user 1's to be read shelf");
    }

    @Test
    void addToReadShelf() {
        Shelves shelves = new Shelves(1,1, "username", 1, "title", "author",
                true, false);
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                1, 1, "placeholder");

        List<Shelves> emptyShelf = new ArrayList<>();
        List<Users> userList = List.of(user);
        List<Books> bookList = List.of(books);

        when(usersService.selectUserById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(booksService.getBooksById(1)).thenReturn(bookList);
        when(shelvesDAO.checkIfOnReadShelf(1, 1)).thenReturn(emptyShelf);
        when(shelvesDAO.addToReadShelf(1, 1)).thenReturn(1);

        assertThat(usersService.selectUserById(1)).isNotNull();

        int checkIfBookExists = booksService.getBooksById(1).size();
        assertThat(checkIfBookExists).isEqualTo(1);

        int checkIfOnReadShelf = shelvesDAO.checkIfOnReadShelf(1, 1).size();
        assertThat(checkIfOnReadShelf).isEqualTo(0);

        assertEquals(1, underTest.addToReadShelf(1, 1));
    }

    @Test
    void checkExceptionWhenBookIsAlreadyOnReadShelf() {
        Shelves shelves = new Shelves(1,1, "username", 1, "title", "author",
                true, false);
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                1, 1, "placeholder");

        List<Shelves> shelvesList = List.of(shelves);
        List<Users> userList = List.of(user);
        List<Books> bookList = List.of(books);

        when(usersService.selectUserById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(booksService.getBooksById(1)).thenReturn(bookList);
        when(shelvesDAO.checkIfOnReadShelf(1, 1)).thenReturn(shelvesList);
        when(shelvesDAO.addToReadShelf(1, 1)).thenReturn(1);

        assertThatThrownBy(() -> underTest.addToReadShelf(1, 1))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("book is already on read shelf");

    }

    @Test
    void addToTBR() {
        Shelves shelves = new Shelves(1,1, "username", 1, "title", "author",
                false, true);
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                1, 1, "placeholder");

        List<Shelves> emptyShelf = new ArrayList<>();
        List<Users> userList = List.of(user);
        List<Books> bookList = List.of(books);

        when(usersService.selectUserById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(booksService.getBooksById(1)).thenReturn(bookList);
        when(shelvesDAO.checkIfOnTBR(1, 1)).thenReturn(emptyShelf);
        when(shelvesDAO.addToTBR(1, 1)).thenReturn(1);

        assertThat(usersService.selectUserById(1)).isNotNull();

        int checkIfBookExists = booksService.getBooksById(1).size();
        assertThat(checkIfBookExists).isEqualTo(1);

        int checkIfOnTBR = shelvesDAO.checkIfOnTBR(1, 1).size();
        assertThat(checkIfOnTBR).isEqualTo(0);

        assertEquals(1, underTest.addToTBR(1, 1));
    }

    @Test
    void checkExceptionWhenBookIsAlreadyOnTBR() {
        Shelves shelves = new Shelves(1,1, "username", 1, "title", "author",
                false, true);
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                1, 1, "placeholder");

        List<Shelves> shelvesList = List.of(shelves);
        List<Users> userList = List.of(user);
        List<Books> bookList = List.of(books);

        when(usersService.selectUserById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(booksService.getBooksById(1)).thenReturn(bookList);
        when(shelvesDAO.checkIfOnTBR(1, 1)).thenReturn(shelvesList);
        when(shelvesDAO.addToTBR(1, 1)).thenReturn(1);

        assertThatThrownBy(() -> underTest.addToTBR(1, 1))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("book is already on to be read shelf");

    }

    @Test
    void removeFromReadShelf() {
        Shelves shelves = new Shelves(1,1, "username", 1, "title", "author",
                true, false);

        List<Shelves> shelvesList = List.of(shelves);

        when(shelvesDAO.selectShelfById(1)).thenReturn(Optional.ofNullable(shelvesList.get(0)));
        when(shelvesDAO.removeFromReadShelf(1)).thenReturn(1);

        assertEquals(1, underTest.removeFromReadShelf(1));

    }

    @Test
    void removeFromTBR() {
        Shelves shelves = new Shelves(1,1, "username", 1, "title", "author",
                false, true);

        List<Shelves> shelvesList = List.of(shelves);

        when(shelvesDAO.selectShelfById(1)).thenReturn(Optional.ofNullable(shelvesList.get(0)));
        when(shelvesDAO.removeFromTBR(1)).thenReturn(1);

        assertEquals(1, underTest.removeFromTBR(1));
    }

    @Test
    void checkExceptionIfNoShelfWithIdExists() {
        Shelves shelves = new Shelves(1,1, "username", 1, "title", "author",
                false, true);

        List<Shelves> shelvesList = List.of(shelves);

        when(shelvesDAO.selectShelfById(1)).thenReturn(Optional.ofNullable(shelvesList.get(0)));
        when(shelvesDAO.removeFromTBR(1)).thenReturn(1);

        assertThatThrownBy(() -> underTest.removeFromTBR(2))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("no shelf with id 2 found");
    }
}