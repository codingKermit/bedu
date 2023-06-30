package com.care.bedu.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.care.bedu.user.vo.MemberMypageVO;


@Mapper
public interface MemberMypageDAO {
	
	/* 마이페이지 홈(유저아이디 받아오기) */
	public List<MemberMypageVO> getMemberMypage(String userid);
}
