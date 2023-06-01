package com.care.bedu.user.service;

import com.care.bedu.user.vo.memberVO;

public interface memberService {

	void regist(memberVO memberVo);
    boolean isEmailDuplicate(String email);
    boolean isNicknameDuplicate(String nickname);
}
