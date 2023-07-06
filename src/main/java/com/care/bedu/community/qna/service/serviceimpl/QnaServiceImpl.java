package com.care.bedu.community.qna.service.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.qna.dao.LikeCntDAO;
import com.care.bedu.community.qna.dao.QnaDAO;
import com.care.bedu.community.qna.service.QnaService;
import com.care.bedu.community.qna.vo.LikeCntVO;
import com.care.bedu.community.qna.vo.QnaVO;

@Service
public class QnaServiceImpl implements QnaService{
	
	@Autowired private QnaDAO qnaDAO;
	@Autowired private LikeCntDAO likeCntDAO;
	
	// 예시된 날짜(예시: 2023-05-30) 형태로 변환하는 로직
	private String regdates(Date regdate) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String strRegdate = simple.format(regdate);
		return strRegdate;
	}

	//조회
	@Override
	public List<QnaVO> listProc(QnaVO qnaVO) throws Exception{
		qnaVO.setLimit(10);
		qnaVO.setPage((qnaVO.getPage()-1)*qnaVO.getLimit()+1);			//시작할 첫번쨰 글번호 행
		qnaVO.setLimit(qnaVO.getPage()+qnaVO.getLimit()-1);
						//끝 글번호 행
		if(qnaVO.getKeyword() != null && qnaVO.getKeyword() != "") {
			return qnaDAO.viewsearch(qnaVO); 								//키워드검색
		}
		
		List<QnaVO> qnalist = qnaDAO.viewlist(qnaVO);
		for(QnaVO qna : qnalist) {
			qna.setStrQnaDate(regdates(qna.getQnaDate()));
			List<QnaVO> username = qnaDAO.getuserName(qna.getUserName());
			for(QnaVO user : username) {
				qna.setUserName(user.getUserName());
			}
		}
		
		return qnalist;						
	}

	//글등록시 조회수 좋아요 개수 0으로 초기화하여 데이터베이스에 저장
	//글등록시 조회수 조회수 개수 0으로 초기화하여 데이터베이스에 저장
	@Override
	public int boardwrite(QnaVO qnaVO) {
		qnaVO.setRegId(qnaVO.getUserName());
		qnaVO.setQnaCnt(0);			
		qnaVO.setQnaLikeCnt(0);		
		return qnaDAO.viewWrite(qnaVO);
	}

	//조회수 증가
	@Override
	public QnaVO viewone(int qnanum, String userName) {
		int result = likeCntDAO.qnaEqcnt(qnanum, userName);
		if(result == 0) {
			LikeCntVO likeCntVO = new LikeCntVO();
			likeCntVO.setQsBdNum(qnanum);
			likeCntVO.setUserName(userName);
			likeCntVO.setRegId(userName);
			int savenum = likeCntDAO.cntqnaSave(likeCntVO);
			if(savenum == 1) {
				qnaDAO.qnaCntUp(qnanum);
			}
			
		}
		
		QnaVO qnaVO = qnaDAO.viewone(qnanum);
		String srtqnaDate = regdates(qnaVO.getQnaDate());
		qnaVO.setStrQnaDate(srtqnaDate);
		return qnaVO;						//게시글 상세보기
	}

	//게시글 삭제
	@Override
	public int viewdelete(int num) {
		return qnaDAO.viewdelete(num);					
	}

	//게시글 수정
	@Override
	public int viewupdate(QnaVO qnaVO) {
		return qnaDAO.viewupdate(qnaVO);					
	}

	// 게시글 전체 개수 조회
	@Override
	public int getTotal() {
		return qnaDAO.getTotal();						
	}
	//좋아요 증가
	@Override
	public HashMap<String, Object> likeUp(int qnanum, String userName, String regId, String likeyns) throws Exception{//게시글 좋아요 1 증가
		int likeCnt = qnaDAO.likeName(qnanum, userName, likeyns);
		System.out.println("확인!");
		Integer likeyn = likeCntDAO.getlikenum(qnanum, userName, likeyns);
		System.out.println("성공!!");
		HashMap<String, Object> map = new HashMap<>();
		
		if(likeCnt == 0) {
			LikeCntVO likeCntVO = new LikeCntVO();
			likeCntVO.setUserName(userName);
			likeCntVO.setQsBdNum(qnanum);
			likeCntVO.setRegId(regId);
			likeCntVO.setLikeyn(likeyns);
			
			Integer result = likeCntDAO.likeCntSave(likeCntVO);
			
			if(result == 1) {
				Integer getnum = qnaDAO.likeUp(qnanum);
				if(getnum == 1) {
					map.put("likenum", likeyn);
					map.put("result", getnum);
					return map;
				}else {
					map.put("likenum", likeyn);
					map.put("result", getnum);
					return map;
				}
			}else {
				map.put("likenum", likeyn);
				map.put("result", result);
				return map;
			}
		}else {
			if(likeyn != null) {
				map.put("likenum", likeyn);
				map.put("result", 0);
			}
			return map;
		}
	}

	@Override
	public ArrayList<QnaVO> getUserId(String userName) {
		return qnaDAO.getuserId(userName);
	}

	@Override
	public int likeDown(int num, String userName, int likenum) {
		
		int result = likeCntDAO.likedel(likenum);
		
		if(result == 1) {
			return qnaDAO.likeDown(num);
		}else {
			return 0;
		}
	}

}
