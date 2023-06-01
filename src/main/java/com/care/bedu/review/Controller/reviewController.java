package com.care.bedu.review.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.care.bedu.review.service.reviewService;
import com.care.bedu.review.vo.reviewVO;

@RestController
@RequestMapping("/api")
public class reviewController {

    @Autowired
    private reviewService reviewService;

    @GetMapping("/reviews")
    public Page<reviewVO> getAllReviews(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        return reviewService.getAllReviews(page, size);
    }

    @GetMapping("/reviews/more")
    public Page<reviewVO> fetchMoreReviews(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        return reviewService.getMoreReviews(page, size);
    }
    
    @GetMapping("/{id}")
    public reviewVO getReviewById(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping
    public reviewVO createReview(@RequestBody reviewVO reviewVO) {
        return reviewService.createReview(reviewVO);
    }

    @PutMapping("/{id}")
    public reviewVO updateReview(@PathVariable int id, @RequestBody reviewVO updatedReviewVO) {
        return reviewService.updateReview(id, updatedReviewVO);
    }

}