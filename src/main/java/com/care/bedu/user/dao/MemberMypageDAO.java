package com.care.bedu.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.bedu.user.vo.MemberMypageVO;


@Mapper
public interface MemberMypageDAO {
	
	/* 마이페이지 홈(유저아이디 받아오기) */
	public List<MemberMypageVO> getMemberMypage(String userid);
	
	/* 마이페이지 홈(전체보기 개수 추출) */
	public int getLectureCount();
	
	/* 마이페이지 홈(전체보기 첫번째) */
	public List<MemberMypageVO> lectureListInfoFirst(@RequestParam("numOfLecture") int numOfLecture);
	
	/* 마이페이지 홈(전체보기를 눌렀을때 보여지는 수강정보 목록) */
	public List<MemberMypageVO> lectureList(@RequestParam("numOfLecture") int numOfLecture, @RequestParam("lectureCount") int lectureCount);
	
	/* 마이페이지 홈(수강정보 목록) */
	public List<MemberMypageVO> lectureListAll(@RequestParam("start") int start, @RequestParam("numOfPage") int numOfPage);
	
	/* 마이페이지 홈(전체보기 클릭 시 화면이동) */
	public List<MemberMypageVO> getMemberMypageAll(String userid);
}
