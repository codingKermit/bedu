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

        // 비밀글 여부를 확인하여 비밀글인 경우에는 비밀번호 검증을 수행합니다.
        for (InquiryVO inquiry : inquiryList) {
            if (inquiry.isSecret()) {
                if (password == null || !password.equals(inquiry.getPassword())) {
                    // 비밀번호가 일치하지 않는 경우 비밀글 제목과 작성자 정보를 가려서 보여줍니다.
                    inquiry.setTitle("비밀글입니다.");
                }
            }
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