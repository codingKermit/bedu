package com.care.bedu.membership.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.membership.dao.MembershipDAO;
import com.care.bedu.membership.service.MembershipService;
import com.care.bedu.membership.vo.MembershipVO;


@Service
public class MembershipServiceImpl implements MembershipService{

	@Autowired
	MembershipDAO dao;
	
	@Override
	public int getSubscribe(String nickname, String type) {
		
		int result = 0;
		
		System.out.println("nickname : " + nickname);
		System.out.println("type : " + type);
		
		
		MembershipVO vo = new MembershipVO(0, nickname, type, null, null);
		
		return dao.getSub(vo);
	}
}
