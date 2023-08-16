//작성자 원준용

package com.care.bedu.review.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.review.service.ReviewService;
import com.care.bedu.review.vo.ReviewVO;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    // GET 요청을 처리하는 엔드포인트로, 모든 후기 목록을 가져옵니다.
    // page와 size는 요청 매개변수로 받아서 페이징 처리를 수행합니다.
    @RequestMapping("/reviews")
    public List<HashMap<String, Object>> getAllReviews(@RequestParam int page, @RequestParam int size) {
        return reviewService.getAllReviews(page, size);
    }
    
    // GET 요청을 처리하는 엔드포인트로, 특정 후기를 가져옵니다.
    // id는 경로 변수로 받아서 해당 ID에 해당하는 후기를 반환합니다
    @RequestMapping("/{id}")
    public ReviewVO getReviewById(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }
    
    // POST 요청을 처리하는 엔드포인트로, 새로운 후기를 생성합니다.
    // 요청 본문에 있는 ReviewVO 객체를 받아서 후기를 생성하고 생성된 후기를 반환합니다.
    @RequestMapping("/reviews/write")
    public ReviewVO createReview(@RequestBody ReviewVO reviewVO) {
        return reviewService.createReview(reviewVO);
    }
    // GET 요청을 처리하는 엔드포인트로, 검색된 후기 목록을 가져옵니다.
    // keyword는 요청 매개변수로 받아서 검색에 사용합니다.
    @RequestMapping("/reviews/search")
    public List<HashMap<String, Object>> getSearchedReviews(@RequestParam String keyword, String topCate) {
        return reviewService.getSearchedReviews(keyword, topCate);
    }

    

}