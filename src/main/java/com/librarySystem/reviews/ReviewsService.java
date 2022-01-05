package com.librarySystem.reviews;

import com.librarySystem.books.BooksService;
import com.librarySystem.exception.ResourceNotFound;
import com.librarySystem.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsService {

    private ReviewsDAO reviewsDAO;
    private UsersService usersService;
    private BooksService booksService;

    @Autowired
    public ReviewsService(ReviewsDAO reviewsDAO, UsersService usersService, BooksService booksService) {
        this.reviewsDAO = reviewsDAO;
        this.usersService = usersService;
        this.booksService = booksService;
    }

    public List<Reviews> getReviewsByBook (int bookid){
        if(reviewsDAO.getReviewsByBook(bookid).isEmpty()){
            throw new ResourceNotFound("no reviews available");
        }
        return reviewsDAO.getReviewsByBook(bookid);
    }

    public List<Reviews> getReviewsByUser (int userid){
        if(reviewsDAO.getReviewsByUser(userid).isEmpty()){
            throw new ResourceNotFound("no reviews found");
        }
        return reviewsDAO.getReviewsByUser(userid);
    }

    public int addReview (int userid, int bookid, Reviews reviews){
        if(usersService.selectUserById(userid).isEmpty()){
            throw new ResourceNotFound("user with id " +userid+ " doesn't exist");
        }
        else if (booksService.getBooksById(bookid).isEmpty()){
            throw new ResourceNotFound("no books with the id " +bookid+ " found");
        }
        return reviewsDAO.addReview(userid, bookid, reviews);
    }

    public int editReview (int id, Reviews reviews){
        if(reviewsDAO.getReviewById(id).isEmpty()){
            throw new ResourceNotFound("review doesn't exist");
        }
        return reviewsDAO.editReview(id, reviews);
    }

    public int deleteReview (int id){
        if(reviewsDAO.getReviewById(id).isEmpty()){
            throw new ResourceNotFound("review doesn't exist");
        }
        return reviewsDAO.deleteReview(id);
    }

    public Reviews getReviewById(int id){
        return reviewsDAO.getReviewById(id).orElseThrow(() -> new ResourceNotFound("review doesn't exist"));
    }
}
