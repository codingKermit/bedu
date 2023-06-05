package com.care.bedu.community.freeBoard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.freeBoard.dao.FreeDAO;
import com.care.bedu.community.freeBoard.vo.FreeVO;

@Service
public class FreeServiceImpl implements FreeService{
	
	@Autowired
	private FreeDAO freeDAO;
	
	@Override
	public ArrayList<FreeVO> listProc(FreeVO freeVO) {
		freeVO.setPage((freeVO.getPage()-1)*5);			//시작할 현재 페이지 로직
		if(freeVO.getKeyword() != null) {				//검색 키워드 조건 (키워드 없으면 기본 조회)
			return freeDAO.viewsearch(freeVO);
		}
		return freeDAO.viewlist(freeVO);
	}

	@Override
	public int boardwrite(FreeVO freeVO) {
		freeVO.setUser_id("test@test.com"); //테스트를 위해 임시로 넣음 회원객체에서 userId가지고 와야함
		freeVO.setReg_id(freeVO.getUser_id());
		freeVO.setComm_cnt(0);
		freeVO.setComm_like_yn(0);
		return freeDAO.viewWrite(freeVO);
	}

	@Override
	public FreeVO viewone(int num) {
		freeDAO.cntUp(num);
		return freeDAO.viewone(num);
	}

	@Override
	public int viewdelete(int num) {
		return freeDAO.viewdelete(num);
	}

	@Override
	public int viewupdate(FreeVO freeVO) {
		return freeDAO.viewupdate(freeVO);
	}

	@Override
	public int getTotal() {										//최종 끝 페이지
		return freeDAO.getTotal();
	}

	@Override
	public int likeUp(int num) {
		return freeDAO.likeUp(num);	
	}
	
	

}
