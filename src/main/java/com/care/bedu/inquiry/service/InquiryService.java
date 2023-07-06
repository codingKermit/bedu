package com.care.bedu.inquiry.service;

import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;

import com.care.bedu.inquiry.vo.InquiryVO;

public interface InquiryService {

	public ArrayList<InquiryVO> inquirylist(InquiryVO inquiryVO) throws Exception; //게시글 리스트 5개 조회
	public int inquiryWrite(InquiryVO inquiryVO); //게시글 작성
	public InquiryVO inquiryone(int num); //게시글 상세보기
	public int getTotal(); //게시글 전체 갯수
	public ArrayList<InquiryVO> getUserName(String userName); //id 가져오기
	
}