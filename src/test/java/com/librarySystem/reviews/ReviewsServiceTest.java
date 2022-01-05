package com.librarySystem.reviews;

import com.librarySystem.books.BooksService;
import com.librarySystem.users.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewsServiceTest {

    private ReviewsDAO reviewsDAO;
    private UsersService usersService;
    private BooksService booksService;
    private ReviewsService underTest;

    @BeforeEach
    void setUp() {
        reviewsDAO = mock(ReviewsDAO.class);
        underTest = new ReviewsService(reviewsDAO, usersService, booksService);
    }


    @Test
    void getReviewsByBook() {
        Reviews reviews = new Reviews(1, 1, "user", 1, "title", "author",
                "review", 5);

        List<Reviews> reviewList = List.of(reviews);

        when(reviewsDAO.getReviewsByBook(1)).thenReturn(reviewList);

        int actual = underTest.getReviewsByBook(1).size();

        assertThat(actual).isEqualTo(1);
    }

    @Test
    void getReviewsByUser() {
        Reviews reviews = new Reviews(1, 1, "user", 1, "title", "author",
                "review", 5);

        List<Reviews> reviewList = List.of(reviews);

        when(reviewsDAO.getReviewsByUser(1)).thenReturn(reviewList);

        int actual = underTest.getReviewsByUser(1).size();

        assertThat(actual).isEqualTo(1);
    }

    @Test
    void addReview() {
        Reviews reviews = new Reviews(1, 1, "user", 1, "title", "author",
                "review", 5);

        when(reviewsDAO.addReview(1,1, reviews)).thenReturn(1);

        assertEquals(1, reviewsDAO.addReview(1, 1, reviews));
    }

    @Test
    void editReview() {
        Reviews reviews = new Reviews(1, 1, "user", 1, "title", "author",
                "review", 5);

        when(reviewsDAO.editReview(1, reviews)).thenReturn(1);

        assertEquals(1, reviewsDAO.editReview(1, reviews));


    }

    @Test
    void deleteReview() {
        Reviews reviews = new Reviews(1, 1, "user", 1, "title", "author",
                "review", 5);

        List<Reviews> reviewList = List.of(reviews);

        when(reviewsDAO.getReviewById(1)).thenReturn(Optional.ofNullable(reviewList.get(0)));
        when(reviewsDAO.deleteReview(1)).thenReturn(1);

        assertThat(reviewsDAO.getReviewById(1)).isNotNull();
        assertThat(underTest.deleteReview(1)).isEqualTo(1);


    }
}