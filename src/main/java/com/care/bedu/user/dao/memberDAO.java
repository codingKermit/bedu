package com.care.bedu.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.user.vo.memberVO;

@Mapper
public interface memberDAO {

	// 회원 가입
	void regist(memberVO member);
	
    // 이메일 중복 체크
    int countByEmail(String email);

    // 닉네임 중복 체크
    int countByNickname(String nickname);
}
