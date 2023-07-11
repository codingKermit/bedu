package com.care.bedu.inquiry.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.inquiry.vo.InquiryVO;

@Mapper
public interface InquiryDAO {
	
	//리스트 조회
	public List<InquiryVO> inquirylist() throws SQLException;
	
	//게시글 작성
	public int inquiryWriteSave(InquiryVO inquiryVO); 
	
	//게시글 상세 보기 num 보기 
	public InquiryVO inquiryone(Integer num); 
	
	//게시글 전체 개수 조회
	public int getTotal(); 
	
	//유저 아이디 조회
	public ArrayList<InquiryVO> getuserName(String userName);
	
	public InquiryVO inquirypassword(Integer password);
	
}