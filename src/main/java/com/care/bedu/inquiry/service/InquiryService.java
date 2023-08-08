package com.care.bedu.inquiry.service;

import java.util.ArrayList;
import java.util.List;

import com.care.bedu.inquiry.vo.InquiryVO;

public interface InquiryService {
	
	//게시글 리스트
	public List<InquiryVO> list() throws Exception;
	
	//검색 게시글 리스트
	public List<InquiryVO> inquiryList(String keyword) throws Exception;
	
	//게시글 상세보기   
	public InquiryVO inquiryone(Integer vocNum);
	
	//게시글 삭제
	public int inquirydelete(Integer vocNum);
	
	//게시글 전체 갯수
	public int getTotal();
	
	//유저아이디조회
	public ArrayList<InquiryVO> getUserId(String userName);	
	
	//글 등록 저장
	public int inquiryWriteSave(InquiryVO inquiryVO);
	
	//게시글 비밀번호	
//	public InquiryVO inquirypassword(Integer password);
	
}