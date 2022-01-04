package com.librarSystem.shelves;

import java.util.Objects;

public class Shelves {

    private int id;
    private int userid;
    private int bookid;
    private boolean readBooks;
    private boolean toBeRead;

    public Shelves(int id, int userid, int bookid, boolean readBooks, boolean toBeRead) {
        this.id = id;
        this.userid = userid;
        this.bookid = bookid;
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

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
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
        return id == shelves.id && userid == shelves.userid && bookid == shelves.bookid && readBooks == shelves.readBooks && toBeRead == shelves.toBeRead;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, bookid, readBooks, toBeRead);
    }

    @Override
    public String toString() {
        return "Shelves{" +
                "id=" + id +
                ", userid=" + userid +
                ", bookid=" + bookid +
                ", readBooks=" + readBooks +
                ", toBeRead=" + toBeRead +
                '}';
    }
}
