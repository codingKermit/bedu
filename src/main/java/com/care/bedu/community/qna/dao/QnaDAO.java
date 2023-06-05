package com.care.bedu.community.qna.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.community.qna.vo.QnaVO;

@Mapper
public interface QnaDAO {
	
	public ArrayList<QnaVO> viewlist(QnaVO qnaDTO);
	public int viewWrite(QnaVO qnaDTO);
	public QnaVO viewone(int num);
	public int viewupdate(QnaVO qnaDTO);
	public int viewdelete(int qnanum);
	public ArrayList<QnaVO> viewsearch(QnaVO qnaDTO);
	public int getTotal();
	public void qnaCntUp(int num);
	public int likeUp(int num);
}
