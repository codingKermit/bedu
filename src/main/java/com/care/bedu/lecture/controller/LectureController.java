package com.care.bedu.lecture.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.lecture.dto.LectureDto;
import com.care.bedu.lecture.service.LectureService;

@RestController
@RequestMapping("/api")
public class LectureController {
	
	@Autowired
	private LectureService service;
	
	@RequestMapping("/lectureList")
	public ArrayList<LectureDto> getLectureList(String category){
		ArrayList<LectureDto> list = new ArrayList<>();
		
		list = service.getLectureList(category);
		
		return list;
		
	}
	
	@RequestMapping("/lectureDetail")
	public LectureDto getLectureDetail(int num) {
		LectureDto dto = new LectureDto();
		
		dto = service.getLectureDetail(num);
		
		return dto;
		
	}

}
