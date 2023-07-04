package com.care.bedu.inquiry.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.inquiry.vo.InquiryVO;

@Mapper
public interface InquiryDAO {
	
	public ArrayList<InquiryVO> inquiryList(InquiryVO inquiryVO)throws SQLException; //게시글 전체 조회
	public int inquiryWrite(InquiryVO inquiryVO); //게시글 작성
	public InquiryVO inquiryone(int num); //게시글 상세보기
	public int getTotal(); //게시글 전체 개수 조회
	public ArrayList<InquiryVO> getuserName(String userName);
	
}