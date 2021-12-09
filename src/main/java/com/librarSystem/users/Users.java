package com.librarSystem.users;

import java.util.Objects;

public class Users {
    private int id;
    private String username;
    private String password;
    private Boolean librarian;

    public Users(int id, String username, String password, Boolean librarian) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.librarian = librarian;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Boolean librarian) {
        this.librarian = librarian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id && Objects.equals(username, users.username) && Objects.equals(password, users.password) && Objects.equals(librarian, users.librarian);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, librarian);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", librarian=" + librarian +
                '}';
    }
}
