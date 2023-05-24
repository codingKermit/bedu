package com.care.bedu.community.qna.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.community.qna.dto.QnaDTO;

@Mapper
public interface QnaDAO {
	
	public ArrayList<QnaDTO> viewlist(QnaDTO communityDTO);
}
