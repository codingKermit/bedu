package com.care.bedu.community.freeBoard.service.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.freeBoard.dao.FreeDAO;
import com.care.bedu.community.freeBoard.service.FreeService;
import com.care.bedu.community.freeBoard.vo.FreeVO;
import com.care.bedu.user.dao.MemberDAO;

@Service
public class FreeServiceImpl implements FreeService{
	
	@Autowired
	private FreeDAO freeDAO;
	
	@Autowired MemberDAO memberDAO;
	
	@Override
	public ArrayList<FreeVO> listProc(FreeVO freeVO) {					//게시글 조회
		freeVO.setLimit(5);
		freeVO.setPage((freeVO.getPage()-1) * freeVO.getLimit()+1);			//시작할 현재(첫번쨰) 글번호 로직
		freeVO.setLimit(freeVO.getPage()+freeVO.getLimit()-1);				
		if(freeVO.getKeyword() != null) {				//검색 키워드 조건 (키워드 없으면 기본 조회)
			return freeDAO.viewsearch(freeVO);
		}
		ArrayList<FreeVO> list = freeDAO.viewlist(freeVO);
		for(FreeVO free : list) {
			free.setStr_comm_date(regdates(free.getComm_date()));
		}
		return list;
	}
	
	// 예시된 날짜(예시: 2023-05-30) 형태로 변환하는 로직
	private String regdates(Date regdate) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String strRegdate = simple.format(regdate);
		return strRegdate;
	}

	@Override
	public int boardwrite(FreeVO freeVO) {
		freeVO.setReg_id(freeVO.getUser_name());
		freeVO.setComm_cnt(0);
		freeVO.setComm_like_yn(0);
		return freeDAO.viewWrite(freeVO);
	}

	@Override
	public FreeVO viewone(int num) {			//게시글 상세보기
		freeDAO.cntUp(num);						//조회수 증가
		FreeVO freeVO = freeDAO.viewone(num);
		freeVO.setStr_comm_date(regdates(freeVO.getComm_date()));
		return freeVO;
	}

	@Override
	public int viewdelete(int num) {			//게시글 삭제
		return freeDAO.viewdelete(num);
	}

	@Override
	public int viewupdate(int comm_num, String title, String content) {			//게시글 수정
		FreeVO freeVO = new FreeVO();
		freeVO.setComm_num(comm_num);
		freeVO.setTitle(title);
		freeVO.setContent(content);
		return freeDAO.viewupdate(freeVO);
	}

	@Override
	public int getTotal() {										//최종 끝 페이지
		return freeDAO.getTotal();
	}

	@Override
	public int likeUp(int num) {					//게시글 좋아요증가
		return freeDAO.likeUp(num);	
	}
	
	

}
