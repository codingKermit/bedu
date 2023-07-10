package com.care.bedu.inquiry.service;

import java.util.ArrayList;
import java.util.List;

import com.care.bedu.inquiry.vo.InquiryVO;

public interface InquiryService {
	
	public List<InquiryVO> list() throws Exception;	
	public InquiryVO inquiryone(int num); //게시글 상세보기
	public int getTotal(); //게시글 전체 갯수
	public ArrayList<InquiryVO> getUserName(String userName); //id 가져오기
	public int inquiryWriteSave(InquiryVO inquiryVO);
	
}