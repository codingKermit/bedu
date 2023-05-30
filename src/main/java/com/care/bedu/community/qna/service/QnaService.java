package com.care.bedu.community.qna.service;

import java.util.ArrayList;

import com.care.bedu.community.qna.dto.QnaDTO;

public interface QnaService {
	
	public ArrayList<QnaDTO> listProc(QnaDTO qnaDTO);
	public int boardwrite(QnaDTO qnaDTO);
	public QnaDTO viewone(int num);
	public int viewdelete(int num);
	public int viewupdate(QnaDTO qnaDTO);
	public int getTotal();
	public int likeUp(int num);
}
