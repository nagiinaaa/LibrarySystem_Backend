package com.librarSystem.libSystem;

import java.util.Objects;

public class LibSystem {
    private String username;
    private String title;
    private String author;

    public LibSystem(String username, String title, String author) {
        this.username = username;
        this.title = title;
        this.author = author;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibSystem libSystem = (LibSystem) o;
        return Objects.equals(username, libSystem.username) && Objects.equals(title, libSystem.title) && Objects.equals(author, libSystem.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, title, author);
    }

    @Override
    public String toString() {
        return "LibSystem{" +
                "username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
