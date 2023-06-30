package com.care.bedu.user.service;

import java.util.List;


import com.care.bedu.user.vo.MemberMypageVO;


public interface MemberMypageService {
	
	/* 마이페이지 홈(유저아이디 받아오기) */
	public List<MemberMypageVO> getMemberMypage(String userid);
}
