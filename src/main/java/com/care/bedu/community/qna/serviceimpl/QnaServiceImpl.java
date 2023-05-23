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
		return qnaDAO.viewlist(qnaDTO);
	}

	

}
