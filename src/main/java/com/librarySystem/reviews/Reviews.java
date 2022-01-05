package com.librarySystem.reviews;

import java.util.Objects;

public class Reviews {
    private int id;
    private int userid;
    private String username;
    private int bookid;
    private String title;
    private String author;
    private String review;
    private int rating;

    public Reviews(int id, int userid, String username, int bookid, String title, String author, String review, int rating) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.bookid = bookid;
        this.title = title;
        this.author = author;
        this.review = review;
        this.rating = rating;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reviews reviews = (Reviews) o;
        return id == reviews.id && userid == reviews.userid && bookid == reviews.bookid && rating == reviews.rating && Objects.equals(username, reviews.username) && Objects.equals(title, reviews.title) && Objects.equals(author, reviews.author) && Objects.equals(review, reviews.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, username, bookid, title, author, review, rating);
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "id=" + id +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", bookid=" + bookid +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }
}
