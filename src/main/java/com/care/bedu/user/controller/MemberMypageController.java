package com.care.bedu.user.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<MemberMypageVO> getMemberMypage(String userid, Model model){

    	/* 처음에 출력할 수강정보 개수 */
		int numOfLecture = 3;
		// model.addAttribute("numOfLecture", numOfLecture);
		
		/* 현재수강정보 전체보기 출력 */
		// int lectureCount = memberMypageService.getLectureCount();
		// model.addAttribute("lectureCount", lectureCount);
		
		/* 처음에 보여지는 수강정보 목록 */
		// model.addAttribute("lectureListFirst", memberMypageService.lectureListInfoFirst(userid, numOfLecture));
		
		return memberMypageService.lectureListInfoFirst(userid, numOfLecture);
    }
    
    /* 마이페이지 홈(전체보기 클릭 시 화면이동) */
    @GetMapping("/mypageAll")
    public ResponseEntity<HashMap<String,Object>> getMemberMypageAll(String userid, Model model, int page, String order, String group, String keyword) {
    	//pageNum에 값이 없으면 1, 있으면 해당하는 페이지를 가져온다.
    	//화면에 보여줄 수강정보의 수
    	int numOfPage = 9;
    	
    	//현재 페이지 번호를 이용해서 출력될 페이지의 시작번호를 구한다.
    	int startNo = (page - 1) * numOfPage;

		HashMap<String, Object> map = new HashMap<>();

		// System.out.println("order : " + order);
		// System.out.println("group : " + group);
		// System.out.println("page : " + page);
		// System.out.println("keyword : " + keyword);

		map.put("list",memberMypageService.getMemberMypageAll(userid, startNo, numOfPage)); 
    	
		map.put("count",memberMypageService.getLectureCount(userid));

		return new ResponseEntity<HashMap<String,Object>>(map, HttpStatus.OK);
    }
}
