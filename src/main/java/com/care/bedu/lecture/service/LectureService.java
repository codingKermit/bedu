package com.care.bedu.lecture.service;

import java.util.ArrayList;

import com.care.bedu.lecture.dto.LectureDto;

public interface LectureService {
	
	public ArrayList<LectureDto> getLectureList(String category);
	public LectureDto getLectureDetail(int num);

}
