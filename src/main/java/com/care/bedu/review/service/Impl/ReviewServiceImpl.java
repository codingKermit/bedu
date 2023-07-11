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
    public List<HashMap<String, Object>> getAllReviews(int page, int size, String tab) {
        int startIndex = (page - 1) * size;
        int endIndex = startIndex + size;

        List<HashMap<String, Object>> allReviews = reviewDAO.getAllReviews();
        if (startIndex > Math.min(endIndex, allReviews.size())) {
            return new ArrayList<>(); // 시작 인덱스가 후기 목록의 범위를 벗어나면 빈 리스트를 반환합니다.
        }

        return allReviews.subList(startIndex, Math.min(endIndex, allReviews.size()));
    }
    
    @Override
    public ReviewVO createReview(ReviewVO reviewVO) {
        // 후기를 생성하는 로직입니다.
        reviewDAO.createReview(reviewVO);
        return reviewVO;
    }

    @Override
    public ReviewVO getReviewById(int id) {
        // 특정 후기를 ID로 가져오는 로직입니다.
        return reviewDAO.getReviewById(id);
    }
    
    @Override
    public List<HashMap<String, Object>> getSearchedReviews(String keyword) {
      // 검색 키워드에 기반하여 후기 필터링
    List<HashMap<String, Object>> allReviews = reviewDAO.getAllReviews();
    List<HashMap<String, Object>> searchedReviews = new ArrayList<>();
    for (HashMap<String, Object> review : allReviews) {
        String title = (String) review.get("TITLE");
        String content = (String) review.get("CONTENT");
        String writer = (String) review.get("WRITER");
        if (title.toLowerCase().contains(keyword) ||
            content.toLowerCase().contains(keyword) ||
            writer.toLowerCase().contains(keyword)) {
        searchedReviews.add(review);
        }
    }
    return searchedReviews;
    }

    
}