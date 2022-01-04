package com.librarSystem.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService {

    private ReviewsDAO reviewsDAO;

    @Autowired
    public ReviewsService(ReviewsDAO reviewsDAO) {
        this.reviewsDAO = reviewsDAO;
    }

    public List<Reviews> getReviewsByBook (int bookid){
        return reviewsDAO.getReviewsByBook(bookid);
    }

    public List<Reviews> getReviewsByUser (int userid){
        return reviewsDAO.getReviewsByUser(userid);
    }

    public int addReview (int userid, int bookid, Reviews reviews){
        return reviewsDAO.addReview(userid, bookid, reviews);
    }

    public int editReview (int id, Reviews reviews){
        return reviewsDAO.editReview(id, reviews);
    }

    public int deleteReview (int id){
        return reviewsDAO.deleteReview(id);
    }
}
