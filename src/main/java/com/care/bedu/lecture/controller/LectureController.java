package com.care.bedu.lecture.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.lecture.dto.LectureDetailDto;
import com.care.bedu.lecture.dto.LectureDto;
import com.care.bedu.lecture.service.LectureService;

@RestController
@RequestMapping("/api")
public class LectureController {
	
	@Autowired
	private LectureService service;
	
	/* 강의 리스트 조회 */
	@RequestMapping("/lectureList")
	public HashMap<String, Object> getLectureList(String category){
		ArrayList<Object> list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		list = service.getLectureList(category);
		
		map.put("item", list);
		return map;
	}
	
	/* 강의 상세정보 조회 */
	@RequestMapping("/lectureDetail")
	public LectureDto getLectureDetail(int num) {
		LectureDto dto = new LectureDto();
		
		dto = service.getLectureDetail(num);
		
		return dto;
	}
	
	/* 분야별 강의 평점순으로 4개씩 조회 */
	@RequestMapping("/getLectureField")
	public HashMap<String,ArrayList<LectureDto>> getLectureField(){
		HashMap<String,ArrayList<LectureDto>> map = new HashMap<>();
		map = service.getLectureField();
		return map;
	}
	
	/* 강의 동영상 목록 조회 */
	@RequestMapping("/getVideoList")
	public ArrayList<LectureDetailDto> getVideoList(int num){
		ArrayList<LectureDetailDto> list = new ArrayList<>();
		
		list = service.getVideoList(num);
		
		return list;
	}

	/* 검색화면 조회 */
	@RequestMapping("/lectureSearch")
	public HashMap<String,ArrayList<LectureDto>> lectureSearch(String keyword, int page){
		HashMap<String, ArrayList<LectureDto>> map = new HashMap<>();
		
		map = service.lectureSearch(keyword, page);
		
		return map;
	}

	/* 검색화면 조회시 토탈 갯수 */
	@RequestMapping("/searchTotal")
	public int searchTotal(String keyword){
		int total = service.searchTotal(keyword);
		return total;
	}

	/* 좋아요한 강의 목록 고유번호 목록 반환 */
	@RequestMapping("/getLikeList")
	public ArrayList<Integer> getLikeList(String userId){
		ArrayList<Integer> list = new ArrayList<>();

		list = service.getLikeList(userId);

		return list;

	}
	

}
