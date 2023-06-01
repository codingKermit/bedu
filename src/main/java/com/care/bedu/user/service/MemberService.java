package com.care.bedu.user.service;

import com.care.bedu.user.vo.memberVO;

public interface MemberService {

	void regist(memberVO memberVo); 
    boolean isEmailDuplicate(String email);
    boolean isNicknameDuplicate(String nickname);
}
