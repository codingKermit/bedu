package com.care.bedu.lecture.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.lecture.service.lectureService;
import com.care.bedu.lecture.vo.lectureDetailVO;
import com.care.bedu.lecture.vo.lectureVO;

@RestController
@RequestMapping("/api")
public class lectureController {
	
	@Autowired
	private lectureService lectureService;
	
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
	public lectureVO getLectureDetail(int num) {
		lectureVO dto = new lectureVO();
		
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
	
	/* 분야별 강의 평점순으로 4개씩 조회 */
	@RequestMapping("/getLectureField")
	public HashMap<String,ArrayList<lectureVO>> getLectureField(){
		HashMap<String,ArrayList<lectureVO>> map = new HashMap<>();
		map = lectureService.getLectureField();
		return map;
	}
	
	/* 강의 동영상 목록 조회 */
	@RequestMapping("/getVideoList")
	public ArrayList<lectureDetailVO> getVideoList(int num){
		ArrayList<lectureDetailVO> list = new ArrayList<>();
		
		list = lectureService.getVideoList(num);
		
		return list;
	}

	/* 검색화면 조회 */
	@RequestMapping("/lectureSearch")
	public HashMap<String,ArrayList<lectureVO>> lectureSearch(String keyword, int page){
		HashMap<String, ArrayList<lectureVO>> map = new HashMap<>();
		
		map = lectureService.lectureSearch(keyword, page);
		
		return map;
	}

	/* 검색화면 조회시 토탈 갯수 */
	@RequestMapping("/searchTotal")
	public int searchTotal(String keyword){
		int total = lectureService.searchTotal(keyword);
		return total;
	}

	/* 좋아요한 강의 목록 고유번호 목록 반환 */
	@RequestMapping("/getLikeList")
	public ArrayList<Integer> getLikeList(String userId){
		ArrayList<Integer> list = new ArrayList<>();

		list = lectureService.getLikeList(userId);

		return list;

	}
	

}
