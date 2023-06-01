package com.care.bedu.review.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.care.bedu.review.service.ReviewService;
import com.care.bedu.review.vo.ReviewVO;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public Page<ReviewVO> getAllReviews(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        return reviewService.getAllReviews(page, size);
    }

    @GetMapping("/reviews/more")
    public Page<ReviewVO> fetchMoreReviews(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        return reviewService.getMoreReviews(page, size);
    }
    
    @GetMapping("/{id}")
    public ReviewVO getReviewById(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping
    public ReviewVO createReview(@RequestBody ReviewVO reviewVO) {
        return reviewService.createReview(reviewVO);
    }

    @PutMapping("/{id}")
    public ReviewVO updateReview(@PathVariable int id, @RequestBody ReviewVO updatedReviewVO) {
        return reviewService.updateReview(id, updatedReviewVO);
    }

}