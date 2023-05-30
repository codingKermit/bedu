package com.care.bedu.community.qna.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.community.qna.dto.QnaDTO;

@Mapper
public interface QnaDAO {
	
	public ArrayList<QnaDTO> viewlist(QnaDTO qnaDTO);
	public int viewWrite(QnaDTO qnaDTO);
	public QnaDTO viewone(int num);
	public int viewupdate(QnaDTO qnaDTO);
	public int viewdelete(int qnanum);
	public ArrayList<QnaDTO> viewsearch(QnaDTO qnaDTO);
	public int getTotal();
	public void qnaCntUp(int num);
	public int likeUp(int num);
}
