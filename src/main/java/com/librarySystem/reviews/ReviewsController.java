package com.librarySystem.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("{userid}/{bookid}")
    public void addReview (@PathVariable("userid") int userid, @PathVariable("bookid") int bookid,
                          @RequestBody Reviews reviews){
        reviewsService.addReview(userid, bookid, reviews);
    }

    @PutMapping("{id}")
    public void editReview (@PathVariable("id") int id, @RequestBody Reviews reviews){
        reviewsService.editReview(id, reviews);
    }

    @DeleteMapping("{id}")
    public void  deleteReview (@PathVariable("id") int id){
        reviewsService.deleteReview(id);
    }
}
