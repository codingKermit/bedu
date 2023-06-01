package com.care.bedu.review.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.care.bedu.review.dao.reviewDAO;
import com.care.bedu.review.service.reviewService;
import com.care.bedu.review.vo.reviewVO;

@Service
public class reviewServiceImpl implements reviewService {
    private final reviewDAO reviewDAO;

    @Autowired
    public reviewServiceImpl(reviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    @Override
    public Page<reviewVO> getAllReviews(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewDAO.getAllReviews(pageable);
    }

    @Override
    public reviewVO createReview(reviewVO reviewVO) {
        reviewDAO.createReview(reviewVO);
        return reviewVO;
    }

    @Override
    public reviewVO updateReview(int id, reviewVO updatedReviewVO) {
    	reviewVO reviewVO = reviewDAO.getReviewById(id);
        if (reviewVO != null) {
            updatedReviewVO.setId(id);
            reviewDAO.updateReview(updatedReviewVO);
            return updatedReviewVO;
        } else {
            throw new RuntimeException("Review not found with id: " + id);
        }
    }

    @Override
    public Page<reviewVO> getMoreReviews(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewDAO.getMoreReviews(pageable);
    }

	@Override
	public reviewVO getReviewById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}