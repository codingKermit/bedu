package com.care.bedu.lecture.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.care.bedu.lecture.dto.LectureDetailDto;
import com.care.bedu.lecture.dto.LectureDto;

public interface LectureService {
	
	public ArrayList<LectureDto> getLectureList(String category, int page);
	public LectureDto getLectureDetail(int num);
	public HashMap<String,ArrayList<LectureDto>> getLectureField();
	public ArrayList<LectureDetailDto> getVideoList(int num);

}
