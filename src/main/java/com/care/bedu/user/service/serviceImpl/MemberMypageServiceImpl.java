package com.care.bedu.user.service.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.care.bedu.user.dao.MemberMypageDAO;
import com.care.bedu.user.service.MemberMypageService;
import com.care.bedu.user.vo.MemberMypageVO;
import com.care.bedu.user.vo.MemberVO;

import jakarta.transaction.Transactional;


@Service
public class MemberMypageServiceImpl implements MemberMypageService {
    private final MemberMypageDAO memberMypageDao;

    @Autowired
    public MemberMypageServiceImpl(MemberMypageDAO memberMypageDao) {
    	this.memberMypageDao = memberMypageDao;
    }
    @Transactional
    @Override
    public List<MemberMypageVO> getMemberMypage(String userid) {
    	return memberMypageDao.getMemberMypage(userid);
    }
}