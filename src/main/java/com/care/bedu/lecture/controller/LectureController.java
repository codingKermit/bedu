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
	public ArrayList<LectureDto> getLectureList(){
		ArrayList<LectureDto> list = new ArrayList<>();
		
		list = service.getLectureList();
		
		return list;
		
	}

}
