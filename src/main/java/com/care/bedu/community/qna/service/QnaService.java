package com.care.bedu.community.qna.service;

import java.util.ArrayList;

import com.care.bedu.community.qna.vo.QnaVO;

public interface QnaService {
	
	public ArrayList<QnaVO> listProc(QnaVO qnaDTO);
	public int boardwrite(QnaVO qnaDTO);
	public QnaVO viewone(int num);
	public int viewdelete(int num);
	public int viewupdate(QnaVO qnaDTO);
	public int getTotal();
	public int likeUp(int num);
}
