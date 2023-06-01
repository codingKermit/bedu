package com.care.bedu.review.service;

import org.springframework.data.domain.Page;
import com.care.bedu.review.vo.ReviewVO;

public interface ReviewService {
    public Page<ReviewVO> getAllReviews(int page, int size);
    public ReviewVO getReviewById(int id);
    public ReviewVO createReview(ReviewVO reviewVO);
    public ReviewVO updateReview(int id, ReviewVO updatedReviewVO);
    public Page<ReviewVO> getMoreReviews(int page, int size);
}