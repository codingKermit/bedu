package com.care.bedu.lecture.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.lecture.vo.LectureDetailVO;
import com.care.bedu.lecture.vo.LectureVO;

@Mapper
public interface LectureDAO {

	
	public ArrayList<LectureVO> getLectureList(HashMap<String, Object> map);
	public LectureVO getLectureDetail(int num);
	public ArrayList<LectureVO> getLectureField(String category);
	public ArrayList<LectureDetailVO> getVideoList(int num);
	public ArrayList<LectureVO> lectureSearch(HashMap<String, Object> arg);
	public int searchTotal(String keyword);
	public ArrayList<Integer> getLikeList(String userId);
	public ArrayList<LectureVO> getNewestLecture();
}
