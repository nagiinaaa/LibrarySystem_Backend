package com.librarSystem.reviews;

import java.util.List;

public interface ReviewsDAO {

    // add review
    // edit review
    // delete review
    // get reviews by book
    // get reviews by user


    List<Reviews> getReviewsByBook (int bookid);
    List<Reviews> getReviewsByUser (int userid);
}
