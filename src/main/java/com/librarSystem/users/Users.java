package com.librarSystem.users;

import java.util.Objects;

public class Users {
    private int id;
    private String username;
    private String password;
    private Boolean librarian;
    private int totalLoans;
    private int currentLoans;
    private int remainingLoans;

    public Users(int id, String username, String password, Boolean librarian, int totalLoans, int currentLoans, int remainingLoans) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.librarian = librarian;
        this.totalLoans = totalLoans;
        this.currentLoans = currentLoans;
        this.remainingLoans = remainingLoans;
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

    public int getTotalLoans() {
        return totalLoans;
    }

    public void setTotalLoans(int totalLoans) {
        this.totalLoans = totalLoans;
    }

    public int getCurrentLoans() {
        return currentLoans;
    }

    public void setCurrentLoans(int currentLoans) {
        this.currentLoans = currentLoans;
    }

    public int getRemainingLoans() {
        return remainingLoans;
    }

    public void setRemainingLoans(int remainingLoans) {
        this.remainingLoans = remainingLoans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id && totalLoans == users.totalLoans && currentLoans == users.currentLoans && remainingLoans == users.remainingLoans && Objects.equals(username, users.username) && Objects.equals(password, users.password) && Objects.equals(librarian, users.librarian);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, librarian, totalLoans, currentLoans, remainingLoans);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", librarian=" + librarian +
                ", totalLoans=" + totalLoans +
                ", currentLoans=" + currentLoans +
                ", remainingLoans=" + remainingLoans +
                '}';
    }
}
