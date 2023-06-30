package com.care.bedu.inquiry.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.inquiry.vo.InquiryVo;

@Mapper
public interface InquiryDao {
    
    // 문의를 생성하는 메서드입니다.
    void createInquiry(InquiryVo inquiryVo);
    
    // 모든 문의를 가져오는 메서드입니다.
    List<HashMap<String, Object>> getAllInquiry();
    
    // 검색된 문의를 가져오는 메서드입니다.
    List<HashMap<String, Object>> getSearchedInquiry(String keyword);
}