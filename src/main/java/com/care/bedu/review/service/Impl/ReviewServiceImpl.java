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
    public List<HashMap<String, Object>> getSearchedReviews(String keyword, String topCate) {
        // 검색 키워드에 기반하여 후기 필터링
    // List<HashMap<String, Object>> getSearchReviews = reviewDAO.getSearchedReviews(keyword, topCate);

    // List<HashMap<String, Object>> searchedReviews = new ArrayList<>();

    // for (HashMap<String, Object> review : getSearchReviews) {
    //     String title = (String) review.get("TITLE");
    //     String content = (String) review.get("CONTENT"); 
    //     String userName = (String) review.get("USER_NAME");
    //     // Null check for title, content, and userName
    //         if (title != null && content != null && userName != null) {
    //             if (title.toLowerCase().contains(keyword.toLowerCase()) ||
    //                 content.toLowerCase().contains(keyword.toLowerCase()) ||
    //                 userName.toLowerCase().contains(keyword.toLowerCase())) {
    //                 searchedReviews.add(review);
    //             }
    //         }
    //     }
        // ReviewVO vo = new ReviewVO();
        // vo.setKeyword(keyword);
        // vo.setTopCate(topCate);
        // System.out.println(vo);
        List<HashMap<String, Object>> sample = reviewDAO.getSearchedReviews(keyword, topCate);
        //System.out.println(sample);
        return sample;
    }


}