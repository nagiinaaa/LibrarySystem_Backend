package com.librarSystem.reviews;

import java.util.List;
import java.util.Optional;

public interface ReviewsDAO {

    List<Reviews> getReviewsByBook (int bookid);
    List<Reviews> getReviewsByUser (int userid);

    Optional<Reviews> getReviewById (int id);

    int addReview(int userid, int bookid, Reviews reviews);
    int editReview (int id, Reviews reviews);
    int deleteReview (int id);
}
