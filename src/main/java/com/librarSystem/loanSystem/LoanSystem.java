package com.librarSystem.loanSystem;

import java.util.Objects;

public class LoanSystem {
    private int id;
    private int userid;
    private String username;
    private int bookid;
    private String title;
    private String author;
    private String bookFormat;

    public LoanSystem(int id, int userid, String username, int bookid, String title, String author) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.bookid = bookid;
        this.title = title;
        this.author = author;
    }

    public LoanSystem(int id, int userid, String username, int bookid, String title, String author, String bookFormat) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.bookid = bookid;
        this.title = title;
        this.author = author;
        this.bookFormat = bookFormat;
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

    public String getBookFormat() {
        return bookFormat;
    }

    public void setBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanSystem libSystem = (LoanSystem) o;
        return id == libSystem.id && userid == libSystem.userid && bookid == libSystem.bookid && Objects.equals(username, libSystem.username) && Objects.equals(title, libSystem.title) && Objects.equals(author, libSystem.author) && Objects.equals(bookFormat, libSystem.bookFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, username, bookid, title, author, bookFormat);
    }

    @Override
    public String toString() {
        return "LibSystem{" +
                "id=" + id +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", bookid=" + bookid +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", bookFormat='" + bookFormat + '\'' +
                '}';
    }
}
