package com.care.bedu.review.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.care.bedu.review.vo.reviewVO;

@Mapper
public interface reviewDAO {
    public reviewVO getReviewById(int id);
    public void createReview(reviewVO reviewVO);
    public void updateReview(reviewVO reviewVO);
    public Page<reviewVO> getAllReviews(Pageable pageable);
    public Page<reviewVO> getMoreReviews(Pageable pageable);
}
