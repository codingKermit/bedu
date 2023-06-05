package com.care.bedu.community.qna.service.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.qna.dao.QnaDAO;
import com.care.bedu.community.qna.service.QnaService;
import com.care.bedu.community.qna.vo.QnaVO;

@Service
public class QnaServiceImpl implements QnaService{
	
	@Autowired private QnaDAO qnaDAO;

	@Override
	public ArrayList<QnaVO> listProc(QnaVO qnaVO) {					
		qnaVO.setPage((qnaVO.getPage()-1)*5);
		if(qnaVO.getKeyword() != null) {
			return qnaDAO.viewsearch(qnaVO);    			//키워드검색
		}
		return qnaDAO.viewlist(qnaVO);						//게시글 조회
	}

	@Override
	public int boardwrite(QnaVO qnaVO) {
		qnaVO.setUser_id("test2@test.com"); //테스트를 위해 임시로 넣음 회원객체에서 userId가지고 와야함
		qnaVO.setReg_id(qnaVO.getUser_id());
		qnaVO.setQna_cnt(0);			//글등록시 조회수 좋아요 개수 0으로 초기화하여 데이터베이스에 저장
		qnaVO.setQna_like_yn(0);		//글등록시 조회수 조회수 개수 0으로 초기화하여 데이터베이스에 저장
		return qnaDAO.viewWrite(qnaVO);
	}

	@Override
	public QnaVO viewone(int num) {
		qnaDAO.qnaCntUp(num);							//조회수 증가
		return qnaDAO.viewone(num);						//게시글 상세보기
	}

	@Override
	public int viewdelete(int num) {
		return qnaDAO.viewdelete(num);					//게시글 삭제
	}

	@Override
	public int viewupdate(QnaVO qnaVO) {
		return qnaDAO.viewupdate(qnaVO);					//게시글 수정
	}

	@Override
	public int getTotal() {
		return qnaDAO.getTotal();						// 게시글 전체 개수 조회
	}

	@Override
	public int likeUp(int num) {
		return qnaDAO.likeUp(num);						//게시글 좋아요 1 증가
	}

}
