package com.care.bedu.user.service;

import java.util.ArrayList;

import com.care.bedu.user.vo.MemberMypageVO;
import com.care.bedu.user.vo.MemberVO;

public interface MemberService {

	void register(MemberVO memberVo); 
    boolean isEmailDuplicate(String email);
    boolean isNicknameDuplicate(String nickname);
    String getPasswordByEmail(String email);
    MemberVO getMemberByEmail(String email);
    ArrayList<MemberMypageVO> getMemberMypage(String userid);
}
