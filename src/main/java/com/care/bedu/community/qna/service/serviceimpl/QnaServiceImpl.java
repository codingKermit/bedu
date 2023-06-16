package com.care.bedu.community.qna.service.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.qna.dao.QnaDAO;
import com.care.bedu.community.qna.service.QnaService;
import com.care.bedu.community.qna.vo.QnaVO;
import com.care.bedu.user.dao.MemberDAO;

@Service
public class QnaServiceImpl implements QnaService{
	
	@Autowired private QnaDAO qnaDAO;
	@Autowired private MemberDAO memberDAO;
	
	// 예시된 날짜(예시: 2023-05-30) 형태로 변환하는 로직
	private String regdates(Date regdate) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String strRegdate = simple.format(regdate);
		return strRegdate;
	}

	//조회
	@Override
	public ArrayList<QnaVO> listProc(QnaVO qnaVO) {
		qnaVO.setLimit(5);
		qnaVO.setPage((qnaVO.getPage()-1)*qnaVO.getLimit() + 1);			//시작할 첫번쨰 글번호 행
		qnaVO.setLimit(qnaVO.getPage()+qnaVO.getLimit()-1);					//끝 글번호 행
		if(qnaVO.getKeyword() != null && qnaVO.getKeyword() != "") {
			return qnaDAO.viewsearch(qnaVO); 								//키워드검색
		}
		ArrayList<QnaVO> list = qnaDAO.viewlist(qnaVO);
		for(QnaVO qna : list) {
			qna.setStr_qna_date(regdates(qna.getQna_date()));
		}
		return list;						//게시글 조회
	}

	@Override
	public int boardwrite(QnaVO qnaVO) {
		qnaVO.setReg_id(qnaVO.getUser_id());
		qnaVO.setQna_cnt(0);			//글등록시 조회수 좋아요 개수 0으로 초기화하여 데이터베이스에 저장
		qnaVO.setQna_like_yn(0);		//글등록시 조회수 조회수 개수 0으로 초기화하여 데이터베이스에 저장
		return qnaDAO.viewWrite(qnaVO);
	}

	@Override
	public QnaVO viewone(int num) {
		qnaDAO.qnaCntUp(num);							//조회수 증가
		QnaVO qnaVO = qnaDAO.viewone(num);
		String srtqnaDate = regdates(qnaVO.getQna_date());
		qnaVO.setStr_qna_date(srtqnaDate);
		return qnaVO;						//게시글 상세보기
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
