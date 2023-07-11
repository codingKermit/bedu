package com.care.bedu.inquiry.service;

import java.util.ArrayList;
import java.util.List;

import com.care.bedu.inquiry.vo.InquiryVO;

public interface InquiryService {
	
	//게시글 리스트
	public List<InquiryVO> list() throws Exception;
	
	//게시글 상세보기
	public InquiryVO inquiryone(int num); //게시글 상세보기
	
	//게시글 전체 갯수
	public int getTotal();
	
	//id 가져오기
	public ArrayList<InquiryVO> getUserName(String userName); 
	
	//글 등록 저장
	public int inquiryWriteSave(InquiryVO inquiryVO);
	
}