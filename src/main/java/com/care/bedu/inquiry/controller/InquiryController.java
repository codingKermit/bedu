package com.care.bedu.inquiry.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


import com.care.bedu.inquiry.service.InquiryService;
import com.care.bedu.inquiry.vo.InquiryVO;

@RestController
@RequestMapping("/api")
public class InquiryController {
 
	@Autowired
	private InquiryService inquiryService;
		
    // 게시글 조회
    @RequestMapping(value="/inquiry/inquiryList", method= {RequestMethod.GET, RequestMethod.POST})
    public List<InquiryVO> inquiryList(@RequestParam(required = false) Integer password) throws Exception {
        List<InquiryVO> inquiryList = inquiryService.list();
        return inquiryList;
    }

    // 게시글 검색 조회
    @RequestMapping(value="/inquiry/inquirySerach", method= {RequestMethod.GET, RequestMethod.POST})
    public List<InquiryVO> inquiryList(@RequestParam(required = false) String keyword) throws Exception {
        List<InquiryVO> inquiryList;
        
        if (keyword != null && !keyword.isEmpty()) {
            inquiryList = inquiryService.inquiryList(keyword);
        } else {
            inquiryList = inquiryService.list();
        }
        return inquiryList;
    } 
	
	@RequestMapping(value="/inquiry/inquiryWrite", method=RequestMethod.POST)			//게시글 작성
	public int inquiryWrite(InquiryVO inquiryVO) {
		return inquiryService.inquiryWriteSave(inquiryVO);
		
	}
	
	@RequestMapping(value="/inquiry/inquiryDetail", method=RequestMethod.GET)			//게시글 상세 보기
	public InquiryVO inquiryDetail(Integer vocNum)  {
		return inquiryService.inquiryone(vocNum);
	}

	@RequestMapping(value="/inquiry/inquiryDelete", method=RequestMethod.GET)
	public int inquirydelete(Integer vocNum) {
		return inquiryService.inquirydelete(vocNum);
	}
	
	
	@RequestMapping(value="/inquiry/inquiryPassword", method=RequestMethod.GET)
	public InquiryVO inquiryPassword(Integer password) {
		return inquiryService.inquirypassword(password);
	}
	
	 @RequestMapping("/inquiry/total")													//게시글 전체 개수 조회
	 public int getTotal(){
	 	return inquiryService.getTotal();
	 }
	 
	 @RequestMapping(value="/inquiry/getUserId", method = RequestMethod.GET)		//현제 로그인된 닉네임에 해당하는 userid조회								
	 public ArrayList<InquiryVO> getUserName(String userName){
		 return inquiryService.getUserId(userName);
	 }
	 
}