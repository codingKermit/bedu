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

//자유게시판
@Service
public class FreeServiceImpl implements FreeService{
	
	@Autowired private FreeDAO freeDAO;
	
	@Autowired private FreeLikeCntDAO freelikeCntDAO; 
	
	//자유게시글 조회
	@Override
	public ArrayList<FreeVO> listProc(FreeVO freeVO) {					
		freeVO.setLimit(10);
		freeVO.setPage((freeVO.getPage()-1) * freeVO.getLimit()+1);			
		freeVO.setLimit(freeVO.getPage()+freeVO.getLimit()-1);				
		if(freeVO.getKeyword() != null) {				
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

	//자유글 작성
	@Override
	public int boardwrite(FreeVO freeVO) {

		freeVO.setCommCnt(0);
		freeVO.setCommLikeYn(0);
		return freeDAO.viewWrite(freeVO);
	}

	
	//게시글 상세보기
	//조회수 증가
	@Override
	public FreeVO viewone(int commnum, String userName){									
		
		int result = freelikeCntDAO.freeEqcnt(commnum, userName);
		
		if(result ==0) {
			LikeCntVO likeCntVO = new LikeCntVO();
			likeCntVO.setCommBdNum(commnum);
			likeCntVO.setUserName(userName);
			likeCntVO.setRegId(userName);
			int savenum =freelikeCntDAO.cntFreeSave(likeCntVO);
			
			freeDAO.cntUp(commnum);
		}
		
		FreeVO freeVO = freeDAO.viewone(commnum);
		freeVO.setStrCommDate(regdates(freeVO.getCommDate()));
		return freeVO;
	}

	//게시글 삭제
	@Override
	public int viewdelete(int num) {			
		return freeDAO.viewdelete(num);
	}

	//게시글 수정
	@Override
	public int viewupdate(int comm_num, String title, String content) {			
		FreeVO freeVO = new FreeVO();
		freeVO.setCommNum(comm_num);
		freeVO.setTitle(title);
		freeVO.setContent(content);
		return freeDAO.viewupdate(freeVO);
	}

	//최종 끝 페이지
	@Override
	public int getTotal() {										
		return freeDAO.getTotal();
	}


	//유저아이디 조회
	@Override
	public ArrayList<FreeVO> getUserId(String userName) {
		return freeDAO.getuserId(userName);
	}
	
	//좋아요 1증가
	@Override
	public HashMap<String, Object> likeUp(int commnum, String userName, String regId, String likeyns) {
		int number = freelikeCntDAO.getfreelikeName(commnum, userName, likeyns);
		
		Integer likeyn = freelikeCntDAO.getlikeFreenum(commnum, userName, likeyns);
		
		
		HashMap<String, Object> map = new HashMap<>();
		if(number == 0) {
			LikeCntVO likeCntVO = new LikeCntVO();
			likeCntVO.setUserName(userName);
			likeCntVO.setCommBdNum(commnum);
			likeCntVO.setRegId(regId);
			likeCntVO.setLikeyn(likeyns);
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
				map.put("result", 0);
				return map;
			}
			return null;
		}
	}

	//좋아요 감소
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
