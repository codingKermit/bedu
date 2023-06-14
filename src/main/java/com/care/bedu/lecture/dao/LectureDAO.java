package com.care.bedu.lecture.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.lecture.vo.LectureDetailVO;
import com.care.bedu.lecture.vo.LectureVO;
import com.care.bedu.review.vo.ReviewVO;

@Mapper
public interface LectureDAO {
	public ArrayList<LectureVO> getLectureList(HashMap<String, Object> map); /* 강의 목록 조회 */
	public LectureVO getLectureDetail(int num); /* 강의 상세페이지 조회 */
	public ArrayList<LectureVO> getLectureField(String category); /* 카테고리별 평점순 4개 조회 => 사용 안함 */
	public ArrayList<LectureDetailVO> getVideoList(int num); /* 강의에 포함된 동영상 목록 조회 */
	public ArrayList<LectureVO> lectureSearch(HashMap<String, Object> arg); /* 검색 키워드로 조회 */
	public int searchTotal(String keyword); /* 키워드로 검색되는 강의 총 갯수 */
	public ArrayList<Integer> getLikeList(String userId); /* 좋아요한 강의 고유번호 목록 */
	public ArrayList<LectureVO> getNewestLecture(); /* 최신순으로 강의 4개 조회 */
	public ArrayList<ReviewVO> getReview(int num); /* 후기 조회 */
	public int addToCart(HashMap<String, Object> arg); /* 장바구니 추가 */
	public ArrayList<LectureVO> getCart(int num); /* 장바구니 조회 */
	public int removeFromCart(HashMap<String, Object> arg); /* 장바구니에서 삭제 */
	public ArrayList<LectureDetailVO> getLesson(int num); /* 동영상 재생 정보 조회 */
}
