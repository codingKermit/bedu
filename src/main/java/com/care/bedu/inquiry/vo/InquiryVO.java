package com.care.bedu.inquiry.vo;

import java.util.Date;

import lombok.Data;

@Data
public class InquiryVO {
	
	// 글번호
	private Integer vocNum; 
	
	// 제목
	private String title; 
	
	// 내용
	private String content; 
	
	// .파일 왼쪽 부분
	private String fileName; 
	
	// .파일 우측 부분
	private String fileType; 
	
	// 유저 이름
	private String userName; 
	
	// 문의 시간
	private Date regDate; 
	
	// 타 테이블에서 구분
	private String regId; 
	
	//검색 키워드
	private String keyword;		
	
}
