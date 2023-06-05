package com.care.bedu.user.service;

import com.care.bedu.user.vo.MemberVO;

public interface MemberService {

	void register(MemberVO memberVo); 
    boolean isEmailDuplicate(String email);
    boolean isNicknameDuplicate(String nickname);
    String getPasswordByEmail(String email);
}
