package com.care.bedu.user.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/mypage")
    public List<MemberMypageVO> getMemberMypage(int userNum, Model model){

    	/* 처음에 출력할 수강정보 개수 */
		int numOfLecture = 3;
		model.addAttribute("numOfLecture", numOfLecture);
		
		/* 현재수강정보 전체보기 출력 */
		int lectureCount = memberMypageService.getLectureCount();
		model.addAttribute("lectureCount", lectureCount);
		
		/* 처음에 보여지는 수강정보 목록 */
		model.addAttribute("lectureListFirst", memberMypageService.lectureListInfoFirst(numOfLecture));
		
		/* 전체보기를 눌렀을때 보여지는 수강정보 목록 */
		//model.addAttribute("lectureList", memberMypageService.lectureList(numOfLecture,lectureCount));
    	
    	return memberMypageService.lectureListInfoFirst(numOfLecture);
    }
    
    /* 마이페이지 홈(전체보기 클릭 시 화면이동) */
    @GetMapping("/mypageAll")
    public List<MemberMypageVO> getMemberMypageAll(int userNum, Model model, @PathVariable Optional<Integer> pageNum) {

    	//pageNum에 값이 없으면 1, 있으면 해당하는 페이지를 가져온다.
    	int pageNumber = pageNum.isPresent() ? (int)pageNum.get() : 1;
    	//화면에 보여줄 수강정보의 수
    	int numOfPage = 10;
    	
    	//구한 값을 뷰 페이지로 보내준다.
    	model.addAttribute("pageNumber", pageNumber);
    	model.addAttribute("numOfPage", numOfPage);
    	
    	//현재 페이지 번호를 이용해서 출력될 페이지의 시작번호를 구한다.
    	int startNo = (pageNumber - 1) * numOfPage;
    	
    	model.addAttribute("list", memberMypageService.getMemberMypageAll(userNum, startNo, numOfPage));
    	
    	
    	return memberMypageService.getMemberMypageAll(userNum, startNo, numOfPage);
    }
}
