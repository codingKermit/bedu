package com.care.bedu.user.service;

import java.util.List;

import com.care.bedu.user.vo.MemberVO;

public interface MemberService {

	void register(MemberVO memberVo); 
    boolean isEmailDuplicate(String email);
    boolean isNicknameDuplicate(String nickname);
    String getPasswordByEmail(String email);
    MemberVO getMemberByEmail(String email);
    List<Integer> getLikedBoardNumbersByEmail(String email);
    /* 구독 정보 조회 */
    boolean getSubInfo(String nickname);
	
}
