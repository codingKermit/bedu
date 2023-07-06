package com.care.bedu.community.freeBoard.service.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.freeBoard.dao.FreeDAO;
import com.care.bedu.community.freeBoard.dao.FreeLikeCntDAO;
import com.care.bedu.community.freeBoard.service.FreeService;
import com.care.bedu.community.freeBoard.vo.FreeVO;
import com.care.bedu.community.qna.vo.LikeCntVO;

@Service
public class FreeServiceImpl implements FreeService{
	
	@Autowired private FreeDAO freeDAO;
	
	@Autowired private FreeLikeCntDAO freelikeCntDAO; 
	
	@Override
	public ArrayList<FreeVO> listProc(FreeVO freeVO) {					//게시글 조회
		freeVO.setLimit(10);
		freeVO.setPage((freeVO.getPage()-1) * freeVO.getLimit()+1);			//시작할 현재(첫번쨰) 글번호 로직
		freeVO.setLimit(freeVO.getPage()+freeVO.getLimit()-1);				
		if(freeVO.getKeyword() != null) {				//검색 키워드 조건 (키워드 없으면 기본 조회)
			return freeDAO.viewsearch(freeVO);
		}
		ArrayList<FreeVO> list = freeDAO.viewlist(freeVO);
		for(FreeVO free : list) {
			free.setStrCommDate(regdates(free.getCommDate()));
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

		freeVO.setCommCnt(0);
		freeVO.setCommLikeYn(0);
		return freeDAO.viewWrite(freeVO);
	}

	@Override
	public FreeVO viewone(int num) {			//게시글 상세보기
		freeDAO.cntUp(num);						//조회수 증가
		FreeVO freeVO = freeDAO.viewone(num);
		freeVO.setStrCommDate(regdates(freeVO.getCommDate()));
		return freeVO;
	}

	@Override
	public int viewdelete(int num) {			//게시글 삭제
		return freeDAO.viewdelete(num);
	}

	@Override
	public int viewupdate(int comm_num, String title, String content) {			//게시글 수정
		FreeVO freeVO = new FreeVO();
		freeVO.setCommNum(comm_num);
		freeVO.setTitle(title);
		freeVO.setContent(content);
		return freeDAO.viewupdate(freeVO);
	}

	@Override
	public int getTotal() {										//최종 끝 페이지
		return freeDAO.getTotal();
	}


	@Override
	public ArrayList<FreeVO> getUserId(String userName) {
		return freeDAO.getuserId(userName);
	}

	@Override
	public HashMap<String, Object> likeUp(int commnum, String userName, String regId) throws Exception {
		int number = freelikeCntDAO.getfreelikeName(commnum, userName);
		Integer likeyn = freelikeCntDAO.getlikeFreenum(commnum, userName);
		HashMap<String, Object> map = new HashMap<>();
		
		if(number == 0) {
			LikeCntVO likeCntVO = new LikeCntVO();
			likeCntVO.setUserName(userName);
			likeCntVO.setCommBdNum(commnum);
			likeCntVO.setRegId(regId);
			Integer result = freelikeCntDAO.likeCntFreeSave(likeCntVO);
			
			if(result == 1) {
				Integer getnum = freeDAO.freelikeUp(commnum);
				map.put("likenum", likeyn);
				map.put("result", getnum);
				return map;
			}else {
		
				map.put("likenum", likeyn);
				map.put("result", result);
				return map;
			}
		}else {
			if(likeyn != null) {
				map.put("likenum", likeyn);
			}
			map.put("result", 0);
			return map;
		}
	}

	@Override
	public int likeDown(int commnum, String userName, int likenum) {
		
		 int result = freelikeCntDAO.freelikedel(likenum, commnum);
		 if(result == 1) {
		   return freeDAO.freelikeDown(commnum);
		 }else {
			 return 0;
		} 
	}
	
	

}
