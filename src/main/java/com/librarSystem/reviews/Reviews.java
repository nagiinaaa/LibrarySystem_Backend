package com.librarSystem.reviews;

import java.util.Objects;

public class Reviews {
    private int id;
    private int userid;
    private int bookid;
    private String review;
    private int rating;

    public Reviews(int id, int userid, int bookid, String review, int rating) {
        this.id = id;
        this.userid = userid;
        this.bookid = bookid;
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

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
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
        return id == reviews.id && userid == reviews.userid && bookid == reviews.bookid && rating == reviews.rating && Objects.equals(review, reviews.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, bookid, review, rating);
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "id=" + id +
                ", userid=" + userid +
                ", bookid=" + bookid +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }
}
