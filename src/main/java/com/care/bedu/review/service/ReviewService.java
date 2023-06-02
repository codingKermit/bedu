package com.care.bedu.review.service;

import java.util.HashMap;
import java.util.List;

import com.care.bedu.review.vo.ReviewVO;

public interface ReviewService {
	List<HashMap<String, Object>> getAllReviews(int page, int size);
    ReviewVO getReviewById(int id);
    ReviewVO createReview(ReviewVO reviewVO);
}