package com.care.bedu.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.user.vo.MemberVo;

@Mapper
public interface MemberMapper {

	// 회원 가입
	void regist(MemberVo member);
	
    // 이메일 중복 체크
    int countByEmail(String email);

    // 닉네임 중복 체크
    int countByNickname(String nickname);
}
