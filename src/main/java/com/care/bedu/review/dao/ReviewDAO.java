package com.care.bedu.review.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.review.vo.ReviewVO;

@Mapper
public interface ReviewDAO {
     ReviewVO getReviewById(int id);
     void createReview(ReviewVO reviewVO);
     List<HashMap<String, Object>> getAllReviews();
}
