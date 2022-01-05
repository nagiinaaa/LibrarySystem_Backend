package com.librarySystem.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersServiceTest {

    private UsersDAO usersDAO;
    private UsersService underTest;

    @BeforeEach
    void setUp() {
        usersDAO = mock(UsersDAO.class);
        underTest = new UsersService(usersDAO);
    }

    @Test
    void getAllUsers() {
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Users> usersList = List.of(user);

        when(usersDAO.getAllUsers()).thenReturn(usersList);

        int actual = underTest.getAllUsers().size();
        assertThat(actual).isEqualTo(1);

    }

    @Test
    void checkExceptionWhenNoUsers() {
        assertThatThrownBy(() -> underTest.getAllUsers())
                .isInstanceOf(Exception.class)
                .hasMessageContaining("no users found");
    }

    @Test
    void selectUserById() {
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Users> usersList = List.of(user);

        when(usersDAO.selectUserById(1)).thenReturn(Optional.ofNullable(usersList.get(0)));

        assertThat(underTest.selectUserById(1)).isNotNull();
    }

    @Test
    void checkExceptionUserWithIdDoesntExist() {
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Users> usersList = List.of(user);

        when(usersDAO.selectUserById(1)).thenReturn(Optional.ofNullable(usersList.get(0)));

        assertThatThrownBy(() -> underTest.selectUserById(2))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("user with id 2 doesn't exist");
    }

    @Test
    void getUserId() {
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Users> usersList = List.of(user);

        when(usersDAO.getUserId("librarian")).thenReturn(usersList);

        int actual = underTest.getUserId("librarian").size();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void checkExceptionIfUserWithUsernameDoesntExist(){
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Users> usersList = List.of(user);

        when(usersDAO.getUserId("librarian")).thenReturn(usersList);

        assertThatThrownBy(() -> underTest.getUserId("user"))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("user doesn't exist");
    }

    @Test
    void deleteUser() {
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Users> usersList = List.of(user);

        when(usersDAO.selectUserById(1)).thenReturn(Optional.ofNullable(usersList.get(0)));
        when(usersDAO.deleteUser(1)).thenReturn(1);


        assertThat(underTest.selectUserById(1)).isNotNull();
        assertEquals(1, underTest.deleteUser(1));
    }

    @Test
    void updateUser() {
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Users> usersList = List.of(user);

        when(usersDAO.selectUserById(1)).thenReturn(Optional.ofNullable(usersList.get(0)));
        when(usersDAO.updateUser(1, user)).thenReturn(1);


        assertThat(underTest.selectUserById(1)).isNotNull();
        assertEquals(1, underTest.updateUser(1, user));
    }

    @Test
    void registerUser() {
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        when(usersDAO.registerUser(user)).thenReturn(1);

        assertEquals(1, underTest.registerUser(user));
    }

    @Test
    void checkTotalLoans() {
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        when(usersDAO.checkTotalLoans("librarian")).thenReturn(2);

        int actual = Integer.parseInt(underTest.checkTotalLoans("librarian").toString()
                .replace("[", "").replace("]", ""));

        assertThat(actual).isEqualTo(2);
    }

    @Test
    void updateLoans() {
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        when(usersDAO.updateLoans("librarian", 1, 1)).thenReturn(1);

        assertEquals(1, underTest.updateLoans("librarian", 1, 1));

    }

    @Test
    void checkIfLibrarian() {
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);

        List<Users> usersList = List.of(user);

        when(usersDAO.checkIfLibrarian("librarian")).thenReturn(usersList);

        int actual = underTest.checkIfLibrarian("librarian").size();

        assertThat(actual).isEqualTo(1);
    }
}