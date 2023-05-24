package com.care.bedu.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.care.bedu.review.entity.Review;
import com.care.bedu.review.repository.ReviewRepository;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Review> getReviewById(@PathVariable int id) {
        return reviewRepository.findById(id);
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable int id, @RequestBody Review updatedReview) {
        Optional<Review> existingReview = reviewRepository.findById(id);
        if (existingReview.isPresent()) {
            Review review = existingReview.get();
            review.setTitle(updatedReview.getTitle());
            review.setWriter(updatedReview.getWriter());
            review.setStar(updatedReview.getStar());
            review.setAuthor(updatedReview.getAuthor());
            return reviewRepository.save(review);
        } else {
            throw new RuntimeException("Review not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable int id) {
        reviewRepository.deleteById(id);
    }

    // 추가적인 API 핸들러 등을 구현할 수 있습니다.

}