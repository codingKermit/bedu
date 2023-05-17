package com.care.bedu.lecture.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.lecture.dto.LectureDto;

@Mapper
public interface LectureDao {

	
	public ArrayList<LectureDto> getLectureList();
}
