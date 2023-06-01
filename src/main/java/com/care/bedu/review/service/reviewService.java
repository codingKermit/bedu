package com.care.bedu.review.service;

import org.springframework.data.domain.Page;
import com.care.bedu.review.vo.reviewVO;

public interface reviewService {
    public Page<reviewVO> getAllReviews(int page, int size);
    public reviewVO getReviewById(int id);
    public reviewVO createReview(reviewVO reviewVO);
    public reviewVO updateReview(int id, reviewVO updatedReviewVO);
    public Page<reviewVO> getMoreReviews(int page, int size);
}