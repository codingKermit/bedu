package com.care.bedu.review.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<HashMap<String, Object>> getAllReviews(int page, int size) {
        int startIndex = (page-1) * size;
        int endIndex = startIndex + size;
        List<HashMap<String, Object>> allReviews = reviewDAO.getAllReviews();
        if(startIndex>Math.min(endIndex, allReviews.size())){
            List<HashMap<String, Object>> map = new ArrayList<>();
            return map; 
        }
        
        return allReviews.subList(startIndex, Math.min(endIndex, allReviews.size()));
    }


    @Override
    public ReviewVO createReview(ReviewVO reviewVO) {
        reviewDAO.createReview(reviewVO);
        return reviewVO;
    }


    @Override
    public ReviewVO getReviewById(int id) {
        return reviewDAO.getReviewById(id);
    }
}