package com.care.bedu.membership.dao;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.membership.vo.MembershipVO;

@Mapper
public interface MembershipDAO {

	public int getSub(MembershipVO vo); /* 구독 저장 */
}
