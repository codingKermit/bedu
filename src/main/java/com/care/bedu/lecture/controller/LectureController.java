package com.care.bedu.lecture.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.lecture.service.LectureService;
import com.care.bedu.lecture.vo.LectureDetailVO;
import com.care.bedu.lecture.vo.LectureVO;


@RestController
@RequestMapping("/api/lect")
public class LectureController {
	
	@Autowired
	private LectureService lectureService;

	/* 강의 리스트 조회 */
	@RequestMapping("/lectureList")
	public HashMap<String, Object> getLectureList(String category){
		ArrayList<Object> list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		list = lectureService.getLectureList(category);
		
		map.put("item", list);
		return map;
	}
	
	/* 강의 상세정보 조회 */
	@RequestMapping("/lectureDetail")
	public LectureVO getLectureDetail(int num) {
		LectureVO dto = new LectureVO();
		
		dto = lectureService.getLectureDetail(num);
		
		return dto;
	}

	/* 신규 오픈 강좌 4개 조회 */
	@RequestMapping("/getNewestLecture")
	public HashMap<String, Object> getNewestLecture(){
		HashMap<String, Object> map = new HashMap<>();

		map = lectureService.getNewestLecture();

		return map;
	}
	
	
	/* 강의 동영상 목록 조회 */
	@RequestMapping("/getVideoList")
	public ArrayList<LectureDetailVO> getVideoList(int num){
		ArrayList<LectureDetailVO> list = new ArrayList<>();
		
		list = lectureService.getVideoList(num);
		
		return list;
	}

	/* 검색화면 조회 */
	@RequestMapping("/lectureSearch")
	public HashMap<String,ArrayList<LectureVO>> lectureSearch(String keyword, int page){
		HashMap<String, ArrayList<LectureVO>> map = new HashMap<>();
		
		map = lectureService.lectureSearch(keyword, page);
		
		return map;
	}

	/* 검색화면 조회시 토탈 갯수 */
	@RequestMapping("/searchTotal")
	public int searchTotal(String keyword){
		int total = lectureService.searchTotal(keyword);
		return total;
	}

	/* 강의 후기 조회 */
	@RequestMapping("/getReview")
	public HashMap<String, Object> getReview(int num){
		HashMap<String, Object> map = new HashMap<>();

		map = lectureService.getReview(num);

		return map;
	}

	/* 결제하기 버튼 사용시 장바구니에 추가 */
	@RequestMapping("/addToCart")
	public int addToCart(int lectNum, int userNum){

		int result = 0;
		result = lectureService.addToCart(lectNum, userNum);
		return result;
	}

	/* 회원번호 기준으로 장바구니 조회 */
	@RequestMapping("/getCart")
	public HashMap<String, Object> getCart(int userNum){
		HashMap<String, Object> result = new HashMap<>();
		result = lectureService.getCart(userNum);

		return result;
	}

	/* 장바구니에서 삭제 */
	@RequestMapping("/removeFromCart")
	public HashMap<String, Object> removeFromCart(
		@RequestParam(value = "list") String list,
		int userNum
		){

			System.out.println("userNum : " + userNum);
			System.out.println(list);
		HashMap<String, Object> map = new HashMap<>();

		return map;
	}
}
