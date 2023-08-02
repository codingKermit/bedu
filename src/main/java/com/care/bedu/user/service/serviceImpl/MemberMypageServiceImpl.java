package com.care.bedu.user.service.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.care.bedu.user.dao.MemberMypageDAO;
import com.care.bedu.user.service.MemberMypageService;
import com.care.bedu.user.vo.MemberMypageVO;


import jakarta.transaction.Transactional;


@Service
public class MemberMypageServiceImpl implements MemberMypageService {
    private final MemberMypageDAO memberMypageDao;

    @Autowired
    public MemberMypageServiceImpl(MemberMypageDAO memberMypageDao) {
    	this.memberMypageDao = memberMypageDao;
    }
    @Transactional
    
    /* 마이페이지 홈(유저아이디 받아오기) */
    @Override
    public List<MemberMypageVO> getMemberMypage(String userid) {
    	return memberMypageDao.getMemberMypage(userid);
    }
    
    /* 마이페이지 홈(전체보기 개수 추출) */
    @Override
	public int getLectureCount(String userId) {
		
		return memberMypageDao.getLectureCount(userId);
	}
    
    /* 마이페이지 홈(전체보기 첫번째) */
	@Override
	public List<MemberMypageVO> lectureListInfoFirst(String userid, int numOfLecture) {
		
		return memberMypageDao.lectureListInfoFirst(userid, numOfLecture);
	}
	
	/* 마이페이지 홈(전체보기 클릭 시 화면이동) */
	@Override
	public List<MemberMypageVO> getMemberMypageAll(String userid, int startNo, int numOfPage) {
		
		return memberMypageDao.getMemberMypageAll(userid, startNo, numOfPage);
	}
}