package com.care.bedu.inquiry.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.inquiry.service.InquiryService;
import com.care.bedu.inquiry.vo.InquiryVo;


@RestController
@RequestMapping("/api")
public class InquiryController {
	
	@Autowired
	private InquiryService inquiryService;
	
	  @RequestMapping("/inquiry/inquiryList")
	    public List<HashMap<String, Object>> getAllInquiry(@RequestParam int page, @RequestParam int size) {
	        return inquiryService.getAllInquiry(page, size);
	    }
	    
	    // GET 요청을 처리하는 엔드포인트로, 특정 문의를 가져옵니다.

	    // POST 요청을 처리하는 엔드포인트로, 새로운 문의를 생성합니다.
	    // 요청 본문에 있는 Inquiry 객체를 받아서 문의를 생성하고 생성된 후기를 반환합니다.
	    @RequestMapping("/inquiry/inquiryWrite")
	    public InquiryVo createInquiry(@RequestBody InquiryVo inquiryVo) {
	        return inquiryService.createInquiry(inquiryVo);
	    }
	    // GET 요청을 처리하는 엔드포인트로, 검색된 문의 목록을 가져옵니다.
	    // keyword는 요청 매개변수로 받아서 검색에 사용합니다.
	    @RequestMapping("/inquiry/inquirysearch")
	    public List<HashMap<String, Object>> getSearchedReviews(@RequestParam String keyword) {
	        return inquiryService.getSearchedInquiry(keyword);
	    }
	}