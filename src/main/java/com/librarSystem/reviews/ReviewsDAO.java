package com.librarSystem.reviews;

import java.util.List;

public interface ReviewsDAO {

    // add review
    // edit review
    // delete review

    List<Reviews> getReviewsByBook (int bookid);
    List<Reviews> getReviewsByUser (int userid);

    int addReview(int userid, int bookid, Reviews reviews);
    int editReview (int id, Reviews reviews);
    int deleteReview (int id);
}
