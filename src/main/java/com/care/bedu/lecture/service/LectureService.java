package com.care.bedu.lecture.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.care.bedu.lecture.vo.LectureDetailVO;
import com.care.bedu.lecture.vo.LectureVO;


public interface LectureService {
	
	public ArrayList<Object> getLectureList(String category); // 분야별 강의 목록
	public LectureVO getLectureDetail(int num); // 강의 상세 정보
	public ArrayList<LectureDetailVO> getVideoList(int num); /* 강의 상세 커리큘럼 동영상 */
	public HashMap<String,ArrayList<LectureVO>> lectureSearch(String keyword, int page); /* 동영상 목록 검색 */
	public int searchTotal(String keyword); /* 검색어에 따른 총 데이터 갯수 조회 */
	public ArrayList<Integer> getLikeList(String userId); /* 좋아요한 강의 고유번호 목록 반환 */
	public HashMap<String, Object> getNewestLecture(); /* 신규 오픈 강좌 4개 조회 */
	public HashMap<String, Object> getReview(int num); /* 강의 하단에 보여줄 수강 후기 최신순 5개 조회 */
	public int addToCart(int lectNum, int userNum); /* 장바구니에 추가 */
}
