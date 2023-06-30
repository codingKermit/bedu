package com.care.bedu.inquiry.service;

import java.util.HashMap;
import java.util.List;

import com.care.bedu.inquiry.vo.InquiryVo;


public interface InquiryService {
	
	// 모든 문의를 가져오는 메서드로, page와 size 매개변수로 페이징 처리를 수행합니다.
	List<HashMap<String, Object>> getAllInquiry(int page, int size); 

    // 문의를 생성하는 메서드입니다.
    InquiryVo createInquiry(InquiryVo inquiryVo);
    
    List<HashMap<String, Object>> getSearchedInquiry(String keyword);
}

