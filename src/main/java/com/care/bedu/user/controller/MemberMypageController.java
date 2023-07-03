package com.care.bedu.user.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.user.service.MemberMypageService;
import com.care.bedu.user.vo.MemberMypageVO;


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
    
    /* 마이페이지 홈(전체보기 클릭 시 화면이동) */
    @RequestMapping("/mypageAll")
    public List<MemberMypageVO> getMemberMypageAll(String userid,Model model) {
    	
//    	/* 처음에 출력할 수강정보 개수 */
//		int numOfLecture = 3;
//		model.addAttribute("numOfLecture", numOfLecture);
//		
//		 /* 현재수강정보 전체보기 출력 */
//		int lectureCount = memberMypageService.getLectureCount();
//		model.addAttribute("lecturCount", lectureCount);
//		
//		/* 처음에 보여지는 수강정보 목록 */
//		model.addAttribute("lectureListFirst", memberMypageService.lectureListInfoFirst(numOfLecture));
//		
//		/* 전체보기를 눌렀을때 보여지는 수강정보 목록 */
//		model.addAttribute("lectureList", memberMypageService.lectureList(numOfLecture,lectureCount));
		
		return memberMypageService.getMemberMypageAll(userid);
    }
}
