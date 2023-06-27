package com.care.bedu.user.service;

import java.util.List;

import com.care.bedu.user.vo.MemberMypageVO;

public interface MemberMypageService {

	List<MemberMypageVO> getMemberMypage(String userid);
    
}
