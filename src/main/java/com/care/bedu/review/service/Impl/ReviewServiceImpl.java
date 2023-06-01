package com.care.bedu.review.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.care.bedu.review.dao.ReviewDAO;
import com.care.bedu.review.service.ReviewService;
import com.care.bedu.review.vo.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewDAO reviewDAO;

    @Autowired
    public ReviewServiceImpl(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    @Override
    public Page<ReviewVO> getAllReviews(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewDAO.getAllReviews(pageable);
    }

    @Override
    public ReviewVO createReview(ReviewVO reviewVO) {
        reviewDAO.createReview(reviewVO);
        return reviewVO;
    }

    @Override
    public ReviewVO updateReview(int id, ReviewVO updatedReviewVO) {
    	ReviewVO reviewVO = reviewDAO.getReviewById(id);
        if (reviewVO != null) {
            updatedReviewVO.setId(id);
            reviewDAO.updateReview(updatedReviewVO);
            return updatedReviewVO;
        } else {
            throw new RuntimeException("Review not found with id: " + id);
        }
    }

    @Override
    public Page<ReviewVO> getMoreReviews(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewDAO.getMoreReviews(pageable);
    }

	@Override
	public ReviewVO getReviewById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}