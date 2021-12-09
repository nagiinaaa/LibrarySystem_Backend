package com.librarSystem.books;

import java.util.Objects;

public class Books {
    private int id;
    private String title;
    private String author;
    private String bookFormat;
    private int numberOfCopies;
    private int copiesInUse;
    private int copiesAvailable;
    private String bookCover;

    public Books(int id, String title, String author, String bookFormat, int numberOfCopies, int copiesInUse, int copiesAvailable, String bookCover) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.bookFormat = bookFormat;
        this.numberOfCopies = numberOfCopies;
        this.copiesInUse = copiesInUse;
        this.copiesAvailable = copiesAvailable;
        this.bookCover = bookCover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public int getCopiesInUse() {
        return copiesInUse;
    }

    public void setCopiesInUse(int copiesInUse) {
        this.copiesInUse = copiesInUse;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return id == books.id && numberOfCopies == books.numberOfCopies && copiesInUse == books.copiesInUse && copiesAvailable == books.copiesAvailable && Objects.equals(title, books.title) && Objects.equals(author, books.author) && Objects.equals(bookFormat, books.bookFormat) && Objects.equals(bookCover, books.bookCover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, bookFormat, numberOfCopies, copiesInUse, copiesAvailable, bookCover);
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", bookFormat='" + bookFormat + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", copiesInUse=" + copiesInUse +
                ", copiesAvailable=" + copiesAvailable +
                ", bookCover='" + bookCover + '\'' +
                '}';
    }
}
