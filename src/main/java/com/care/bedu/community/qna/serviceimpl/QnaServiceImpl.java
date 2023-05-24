package com.care.bedu.community.qna.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.qna.dao.QnaDAO;
import com.care.bedu.community.qna.dto.QnaDTO;
import com.care.bedu.community.qna.service.QnaService;

@Service
public class QnaServiceImpl implements QnaService{
	
	@Autowired private QnaDAO qnaDAO;

	@Override
	public ArrayList<QnaDTO> listProc(QnaDTO qnaDTO) {
		if(qnaDTO.getKeyword() != null) {
			return qnaDAO.viewsearch(qnaDTO);
		}
		return qnaDAO.viewlist(qnaDTO);
	}

	@Override
	public int boardwrite(QnaDTO qnaDTO) {
		qnaDTO.setQna_cnt(0);
		qnaDTO.setQna_like_yn(0);
		return qnaDAO.viewwrite(qnaDTO);
	}

	@Override
	public QnaDTO viewone(int num) {
		return qnaDAO.viewone(num);
	}

	@Override
	public int viewdelete(int num) {
		return qnaDAO.viewdelete(num);
	}

	@Override
	public int viewupdate(QnaDTO qnaDTO) {
		return qnaDAO.viewupdate(qnaDTO);
	}

	
	

}
