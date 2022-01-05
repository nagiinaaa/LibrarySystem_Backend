package com.librarySystem.reviews;

import com.librarySystem.books.Books;
import com.librarySystem.books.BooksService;
import com.librarySystem.users.Users;
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
        usersService = mock(UsersService.class);
        booksService = mock(BooksService.class);
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
    void shouldThrowErrorIfNoReviewsForBook(){
        Reviews reviews = new Reviews(1, 1, "user", 1, "title", "author",
                "review", 5);

        List<Reviews> reviewList = List.of(reviews);

        when(reviewsDAO.getReviewsByBook(1)).thenReturn(reviewList);

        assertThatThrownBy(() -> underTest.getReviewsByBook(2))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("no reviews available");
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
    void shouldThrowErrorIfNoReviewsForUser(){
        Reviews reviews = new Reviews(1, 1, "user", 1, "title", "author",
                "review", 5);

        List<Reviews> reviewList = List.of(reviews);

        when(reviewsDAO.getReviewsByUser(1)).thenReturn(reviewList);

        assertThatThrownBy(() -> underTest.getReviewsByUser(2))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("no reviews found");
    }

    @Test
    void addReview() {
        Reviews reviews = new Reviews(1, 1, "user", 1, "title", "author",
                "review", 5);
        Users user = new Users(1, "librarian", "password", true, 2,
                0, 2);
        Books books = new Books(1, "The Diviners", "Libba Bray", "eBook", 2,
                0, 2, "placeholder");


        List<Users> userList = List.of(user);
        List<Books> bookList = List.of(books);

        when(usersService.selectUserById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(booksService.getBooksById(1)).thenReturn(bookList);
        when(reviewsDAO.addReview(1,1, reviews)).thenReturn(1);

        int checkBookExists = booksService.getBooksById(1).size();
        assertThat(checkBookExists).isEqualTo(1);


        assertThat(usersService.selectUserById(1)).isNotNull();

        assertEquals(1, underTest.addReview(1, 1, reviews));
    }

    @Test
    void editReview() {
        Reviews reviews = new Reviews(1, 1, "user", 1, "title", "author",
                "review", 5);

        List<Reviews> reviewList = List.of(reviews);

        when(reviewsDAO.getReviewById(1)).thenReturn(Optional.ofNullable(reviewList.get(0)));
        when(reviewsDAO.editReview(1, reviews)).thenReturn(1);

        assertThat(underTest.getReviewById(1)).isNotNull();
        assertEquals(1, underTest.editReview(1, reviews));
    }

    @Test
    void deleteReview() {
        Reviews reviews = new Reviews(1, 1, "user", 1, "title", "author",
                "review", 5);

        List<Reviews> reviewList = List.of(reviews);

        when(reviewsDAO.getReviewById(1)).thenReturn(Optional.ofNullable(reviewList.get(0)));
        when(reviewsDAO.deleteReview(1)).thenReturn(1);

        assertThat(underTest.getReviewById(1)).isNotNull();
        assertThat(underTest.deleteReview(1)).isEqualTo(1);


    }

    @Test
    void shouldThrowExceptionIfReviewDoesntExist(){
        Reviews reviews = new Reviews(1, 1, "user", 1, "title", "author",
                "review", 5);

        List<Reviews> reviewList = List.of(reviews);

        when(reviewsDAO.getReviewById(1)).thenReturn(Optional.ofNullable(reviewList.get(0)));

        assertThatThrownBy(() -> underTest.getReviewById(2))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("review doesn't exist");
    }

    @Test
    void getReviewById(){
        Reviews reviews = new Reviews(1, 1, "user", 1, "title", "author",
                "review", 5);

        List<Reviews> reviewList = List.of(reviews);

        when(reviewsDAO.getReviewById(1)).thenReturn(Optional.ofNullable(reviewList.get(0)));

        assertThat(underTest.getReviewById(1)).isNotNull();
    }
}