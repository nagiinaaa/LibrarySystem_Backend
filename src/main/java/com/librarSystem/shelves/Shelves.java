package com.librarSystem.shelves;

import java.util.Objects;

public class Shelves {

    private int id;
    private int userid;
    private String username;
    private int bookid;
    private String title;
    private String author;
    private boolean readBooks;
    private boolean toBeRead;

    public Shelves(int id, int userid, String username, int bookid, String title, String author, boolean readBooks, boolean toBeRead) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.bookid = bookid;
        this.title = title;
        this.author = author;
        this.readBooks = readBooks;
        this.toBeRead = toBeRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
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

    public boolean isReadBooks() {
        return readBooks;
    }

    public void setReadBooks(boolean readBooks) {
        this.readBooks = readBooks;
    }

    public boolean isToBeRead() {
        return toBeRead;
    }

    public void setToBeRead(boolean toBeRead) {
        this.toBeRead = toBeRead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelves shelves = (Shelves) o;
        return id == shelves.id && userid == shelves.userid && bookid == shelves.bookid && readBooks == shelves.readBooks && toBeRead == shelves.toBeRead && Objects.equals(username, shelves.username) && Objects.equals(title, shelves.title) && Objects.equals(author, shelves.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, username, bookid, title, author, readBooks, toBeRead);
    }

    @Override
    public String toString() {
        return "Shelves{" +
                "id=" + id +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", bookid=" + bookid +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", readBooks=" + readBooks +
                ", toBeRead=" + toBeRead +
                '}';
    }
}
