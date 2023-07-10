package com.care.bedu.inquiry.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.inquiry.vo.InquiryVO;

@Mapper
public interface InquiryDAO {
	
//	 List<HashMap<String, Object>> getAllReviews();	
	
	public List<InquiryVO> inquirylist() throws SQLException;
	public int inquiryWriteSave(InquiryVO inquiryVO); //게시글 작성
	public InquiryVO inquiryone(int num); //게시글 상세보기 num 상세 보기 번호
	public int getTotal(); //게시글 전체 개수 조회
	public ArrayList<InquiryVO> getuserName(String userName);
	
}