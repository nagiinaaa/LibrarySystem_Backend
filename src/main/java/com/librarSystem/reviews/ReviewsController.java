package com.librarSystem.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "reviews")
public class ReviewsController {

    private ReviewsService reviewsService;

    @Autowired
    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping("book/{bookid}")
    public List<Reviews> getReviewsByBook (@PathVariable("bookid") int bookid){
        return reviewsService.getReviewsByBook(bookid);
    }

    @GetMapping("user/{userid}")
    public List<Reviews> getReviewsByUser (@PathVariable("userid") int userid){
        return reviewsService.getReviewsByUser(userid);
    }
}
