package com.care.bedu.lecture.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.lecture.vo.lectureDetailVO;
import com.care.bedu.lecture.vo.lectureVO;

@Mapper
public interface LectureDAO {

	
	public ArrayList<lectureVO> getLectureList(HashMap<String, Object> map);
	public lectureVO getLectureDetail(int num);
	public ArrayList<lectureVO> getLectureField(String category);
	public ArrayList<lectureDetailVO> getVideoList(int num);
	public ArrayList<lectureVO> lectureSearch(HashMap<String, Object> arg);
	public int searchTotal(String keyword);
	public ArrayList<Integer> getLikeList(String userId);
	public ArrayList<lectureVO> getNewestLecture();
}
