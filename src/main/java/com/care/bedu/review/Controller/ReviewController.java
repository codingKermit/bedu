package com.care.bedu.review.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.review.service.ReviewService;
import com.care.bedu.review.vo.ReviewVO;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    @RequestMapping("/reviews")
    public List<HashMap<String, Object>> getAllReviews(@RequestParam int page, @RequestParam int size) {
        return reviewService.getAllReviews(page, size);
    }

    @RequestMapping("/{id}")
    public ReviewVO getReviewById(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping
    public ReviewVO createReview(@RequestBody ReviewVO reviewVO) {
        return reviewService.createReview(reviewVO);
    }
}