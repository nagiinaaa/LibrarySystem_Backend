package com.librarySystem.loanSystem;

import com.librarySystem.books.Books;
import com.librarySystem.books.BooksService;
import com.librarySystem.exception.ResourceNotFound;
import com.librarySystem.users.Users;
import com.librarySystem.users.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanSystemServiceTest {

    private LoanSystemDAO loanSystemDAO;
    private BooksService booksService;
    private UsersService usersService;
    private LoanSystemService underTest;

    @BeforeEach
    void setUp() {
        loanSystemDAO = mock(LoanSystemDAO.class);
        booksService = mock(BooksService.class);
        usersService = mock(UsersService.class);
        underTest = new LoanSystemService(loanSystemDAO, booksService, usersService);
    }

    @Test
    void checkAllLoans() {
        LoanSystem loanSystem = new LoanSystem(1, 1, "username", 1, "title",
                "author");

        List<LoanSystem> loanSystemList = List.of(loanSystem);

        when(loanSystemDAO.checkAllLoans()).thenReturn(loanSystemList);

        int actual = underTest.checkAllLoans().size();

        assertThat(actual).isEqualTo(1);
    }

    @Test
    void checkExceptionIfNoLoans (){
        assertThatThrownBy(() -> underTest.checkAllLoans())
                .isInstanceOf(Exception.class)
                .hasMessageContaining("no loans found in the system");
    }

    @Test
    void selectLoansByUser() {
        LoanSystem loanSystem = new LoanSystem(1, 1, "username", 1, "title",
                "author");

        List<LoanSystem> loanSystemList = List.of(loanSystem);

        when(loanSystemDAO.selectLoansByUser("username")).thenReturn(loanSystemList);

        int actual = underTest.selectLoansByUser("username").size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void checkExceptionIfUserHasNoLoans() {
        LoanSystem loanSystem = new LoanSystem(1, 1, "username", 1, "title",
                "author");

        List<LoanSystem> loanSystemList = List.of(loanSystem);

        when(loanSystemDAO.selectLoansByUser("username")).thenReturn(loanSystemList);

        assertThatThrownBy(() -> underTest.selectLoansByUser("user"))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("user has no loans");
    }

    @Test
    void selectLoansByTitleAndAuthorAndBookFormat() {
        LoanSystem loanSystem = new LoanSystem(1, 1, "username", 1, "title",
                "author");

        List<LoanSystem> loanSystemList = List.of(loanSystem);

        when(loanSystemDAO.selectLoansByTitleAndAuthorAndBookFormat("title", "author",
                "bookFormat")).thenReturn(loanSystemList);

        int actual = underTest.selectLoansByTitleAndAuthorAndBookFormat("title", "author",
                "bookFormat").size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void checkExceptionIfNoLoansByTitleAndAuthorAndBookFormat(){
        LoanSystem loanSystem = new LoanSystem(1, 1, "username", 1, "title",
                "author");

        List<LoanSystem> loanSystemList = List.of(loanSystem);

        when(loanSystemDAO.selectLoansByTitleAndAuthorAndBookFormat("title", "author",
                "bookFormat")).thenReturn(loanSystemList);

        assertThatThrownBy(() -> underTest.selectLoansByTitleAndAuthorAndBookFormat("not a book",
                "author", "bookFormat"))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("no loans of not a book by author in bookFormat format found");
    }

    @Test
    void selectLoanById() {
        LoanSystem loanSystem = new LoanSystem(1, 1, "username", 1, "title",
                "author");

        List<LoanSystem> loanSystemList = List.of(loanSystem);

        when(loanSystemDAO.selectLoanById(1)).thenReturn(loanSystemList);

        int actual = underTest.selectLoanById(1).size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void checkExceptionIfNoLoanWithIdExists(){
        LoanSystem loanSystem = new LoanSystem(1, 1, "username", 1, "title",
                "author");

        List<LoanSystem> loanSystemList = List.of(loanSystem);

        when(loanSystemDAO.selectLoanById(1)).thenReturn(loanSystemList);

        assertThatThrownBy(() -> underTest.selectLoanById(2))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("no loans with id 2 found");
    }

    @Test
    void selectLoansByTitleAndAuthorAndBookFormatAndUser() {
        LoanSystem loanSystem = new LoanSystem(1, 1, "username", 1, "title",
                "author");

        List<LoanSystem> loanSystemList = List.of(loanSystem);

        when(loanSystemDAO.selectLoansByTitleAndAuthorAndBookFormatAndUser("title", "author",
                "bookFormat", "username")).thenReturn(loanSystemList);

        int actual = underTest.selectLoansByTitleAndAuthorAndBookFormatAndUser("title", "author",
                "bookFormat", "username").size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void checkExceptionIfNoLoansByTitleAndAuthorAndBookFormatAndUser(){
        LoanSystem loanSystem = new LoanSystem(1, 1, "username", 1, "title",
                "author");

        List<LoanSystem> loanSystemList = List.of(loanSystem);

        when(loanSystemDAO.selectLoansByTitleAndAuthorAndBookFormatAndUser("title", "author",
                "bookFormat", "username")).thenReturn(loanSystemList);

        assertThatThrownBy(() -> underTest.selectLoansByTitleAndAuthorAndBookFormatAndUser("not a book",
                "author", "bookFormat", "username"))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("no loans of not a book by author in bookFormat format by username found");
    }

    @Test
    void updateCopyNumbers() {
        LoanSystem loanSystem = new LoanSystem(1, 1, "username", 1, "title",
                "author");

        loanSystemDAO.updateCopyNumbers("title", "authir", "bookFormat");

        verify(loanSystemDAO, times(1)).updateCopyNumbers("title", "authir", "bookFormat");
    }


    @Test
    void updateLoanNumbers() {
        LoanSystem loanSystem = new LoanSystem(1, 1, "username", 1, "title",
                "author");

        loanSystemDAO.updateLoanNumbers("username");

        verify(loanSystemDAO, times(1)).updateLoanNumbers("username");

    }

    @Test
    void returnBook() {
        LoanSystem loanSystem = new LoanSystem(1, 1, "username", 1, "title",
                "author");

        loanSystemDAO.returnBook(1);

        verify(loanSystemDAO, times(1)).returnBook(1);

    }

    @Test
    void borrowBook() {
        LoanSystem loanSystem = new LoanSystem(1, 1, "username", 1, "title",
                "author");

        loanSystemDAO.borrowBook("username", "title", 1, 1);

        verify(loanSystemDAO, times(1)).borrowBook(
                "username", "title", 1, 1);
    }
}