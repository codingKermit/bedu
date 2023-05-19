package com.care.bedu.lecture.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.lecture.dto.LectureDetailDto;
import com.care.bedu.lecture.dto.LectureDto;

@Mapper
public interface LectureDao {

	
	public ArrayList<LectureDto> getLectureList(HashMap<String, Object> map);
	public LectureDto getLectureDetail(int num);
	public ArrayList<LectureDto> getLectureField(String category);
	public ArrayList<LectureDetailDto> getVideoList(int num);
}
