package com.care.bedu.user.service;

import java.util.List;



import com.care.bedu.user.vo.MemberMypageVO;


public interface MemberMypageService {
	
	/* 마이페이지 홈(유저아이디 받아오기) */
	public List<MemberMypageVO> getMemberMypage(String userid);
	
	/* 마이페이지 홈(전체보기 개수 추출) */
	public int getLectureCount();
	
	/* 마이페이지 홈(전체보기 첫번째) */
	public List<MemberMypageVO> lectureListInfoFirst(String userid, int numOfLecture);
	
	/* 마이페이지 홈(전체보기 클릭 시 화면이동) */
	public List<MemberMypageVO> getMemberMypageAll(String userid, int startNo, int numOfPage);
}
