package com.care.bedu.user.service;

import java.util.List;

import com.care.bedu.user.vo.MemberMypageVO;
import com.care.bedu.user.vo.MemberVO;

public interface MemberMypageService {

	List<MemberMypageVO> getMemberMypage(String userid);
    
}
