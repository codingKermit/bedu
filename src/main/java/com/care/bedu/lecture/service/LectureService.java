package com.care.bedu.lecture.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.care.bedu.lecture.dto.LectureDetailDto;
import com.care.bedu.lecture.dto.LectureDto;

public interface LectureService {
	
	public ArrayList<Object> getLectureList(String category); // 분야별 강의 목록
	public LectureDto getLectureDetail(int num); // 강의 상세 정보
	public HashMap<String,ArrayList<LectureDto>> getLectureField(); /* 분야별로 평점순으로 4개씩 조회. 화면 구성이 변경되어서 사용안할듯 */
	public ArrayList<LectureDetailDto> getVideoList(int num); /* 강의 상세 커리큘럼 동영상 */
	public HashMap<String,ArrayList<LectureDto>> lectureSearch(String keyword, int page); /* 동영상 검색 */
	public int searchTotal(String keyword); /* 검색어에 따른 총 데이터 갯수 */
	public ArrayList<Integer> getLikeList(String userId); /* 좋아요한 강의 고유번호 목록 반환 */
}
