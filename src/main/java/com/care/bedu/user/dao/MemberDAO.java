package com.care.bedu.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.user.vo.MemberVO;

@Mapper
public interface MemberDAO {

	// 회원 가입
	void register(MemberVO memberVo);
	
    // 이메일 중복 체크
    int countByEmail(String email);

    // 닉네임 중복 체크
    int countByNickname(String nickname);
    
    String getPasswordByEmail(String email);
    
    MemberVO getMemberByEmail(String email);
    
    String getNicknameByEmail(String email);
    
    List<Integer> getLikedBoardNumbersByEmail(String email);
    
    List<Integer> getLikedQSBoardNumbersByEmail(String email);
    
    /* 구독 정보 조회 */
    int getSubInfo(String nickname);
}
