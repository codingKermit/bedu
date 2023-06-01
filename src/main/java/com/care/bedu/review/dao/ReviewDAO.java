package com.care.bedu.review.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.care.bedu.review.vo.ReviewVO;

@Mapper
public interface ReviewDAO {
    public ReviewVO getReviewById(int id);
    public void createReview(ReviewVO reviewVO);
    public void updateReview(ReviewVO reviewVO);
    public Page<ReviewVO> getAllReviews(Pageable pageable);
    public Page<ReviewVO> getMoreReviews(Pageable pageable);
}
