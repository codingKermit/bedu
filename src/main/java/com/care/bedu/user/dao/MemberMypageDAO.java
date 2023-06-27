package com.care.bedu.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.user.vo.MemberMypageVO;

@Mapper
public interface MemberMypageDAO {

	List<MemberMypageVO> getMemberMypage(String userid);
    
}
