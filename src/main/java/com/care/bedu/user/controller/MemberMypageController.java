package com.care.bedu.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.user.service.MemberMypageService;
import com.care.bedu.user.vo.MemberMypageVO;
import com.care.bedu.user.vo.MemberVO;




@RestController
@RequestMapping("/api")
public class MemberMypageController {
    
	@Autowired
    MemberMypageService memberMypageService;
    

    /* 마이페이지 홈 */
    @RequestMapping("/mypage")
    public List<MemberMypageVO> getMemberMypage(String userid){
    	
    	return memberMypageService.getMemberMypage(userid);
    }
   
}
